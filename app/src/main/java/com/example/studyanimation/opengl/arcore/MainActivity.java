package com.example.studyanimation.opengl.arcore;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.display.DisplayManager;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;

import com.google.ar.core.ArCoreApk;
import com.google.ar.core.Camera;
import com.google.ar.core.Config;
import com.google.ar.core.Frame;
import com.google.ar.core.HitResult;
import com.google.ar.core.PointCloud;
import com.google.ar.core.Pose;
import com.google.ar.core.Session;

import java.util.List;

public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private CheckBox mCheckBox;
    private GLSurfaceView mSurfaceView;
    private MainRenderer mRenderer;

    private boolean mUserRequestedInstall = true;

    private Session mSession;
    private Config mConfig;

    private float[] mProjMatrix = new float[16];
    private float[] mViewMatrix = new float[16];

    private float mLastX;
    private float mLastY;
    private float[] mLastPoint = new float[] { 0.0f, 0.0f, 0.0f };
    private boolean mNewPath = false;
    private boolean mPointAdded = false;

    private static float MIN_DISTANCE = 0.000625f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBarAndTitleBar();
        setContentView(R.layout.activity_main);

        mCheckBox = (CheckBox) findViewById(R.id.check_box);
        mSurfaceView = (GLSurfaceView) findViewById(R.id.gl_surface_view);

        DisplayManager displayManager = (DisplayManager) getSystemService(DISPLAY_SERVICE);
        if (displayManager != null) {
            displayManager.registerDisplayListener(new DisplayManager.DisplayListener() {
                @Override
                public void onDisplayAdded(int displayId) {
                }

                @Override
                public void onDisplayChanged(int displayId) {
                    synchronized (this) {
                        mRenderer.onDisplayChanged();
                    }
                }

                @Override
                public void onDisplayRemoved(int displayId) {
                }
            }, null);
        }

        mRenderer = new MainRenderer(new MainRenderer.RenderCallback() {
            @Override
            public void preRender() {
                if (mRenderer.isViewportChanged()) {
                    Display display = getWindowManager().getDefaultDisplay();
                    int displayRotation = display.getRotation();
                    mRenderer.updateSession(mSession, displayRotation);
                }

                mSession.setCameraTextureName(mRenderer.getTextureId());

                Frame frame = mSession.update();
                if (frame.hasDisplayGeometryChanged()) {
                    mRenderer.transformDisplayGeometry(frame);
                }

                PointCloud pointCloud = frame.acquirePointCloud();
                mRenderer.updatePointCloud(pointCloud);
                pointCloud.release();

                Camera camera = frame.getCamera();
                camera.getProjectionMatrix(mProjMatrix, 0, 0.1f, 100.0f);
                camera.getViewMatrix(mViewMatrix, 0);

                mRenderer.setProjectionMatrix(mProjMatrix);
                mRenderer.updateViewMatrix(mViewMatrix);

                if (mCheckBox.isChecked()) {
                    float[] screenPoint = getScreenPoint(mLastX, mLastY,
                            mRenderer.getWidth(), mRenderer.getHeight(),
                            mProjMatrix, mViewMatrix);
                    if (mNewPath) {
                        mNewPath = false;
                        mRenderer.addPath(screenPoint[0], screenPoint[1], screenPoint[2]);
                        mLastPoint[0] = screenPoint[0];
                        mLastPoint[1] = screenPoint[1];
                        mLastPoint[2] = screenPoint[2];
                    }
                    else if (mPointAdded) {
                        if (checkDistance(screenPoint[0], screenPoint[1], screenPoint[2],
                                mLastPoint[0], mLastPoint[1], mLastPoint[2])) {
                            mRenderer.addPoint(screenPoint[0], screenPoint[1], screenPoint[2]);
                            mLastPoint[0] = screenPoint[0];
                            mLastPoint[1] = screenPoint[1];
                            mLastPoint[2] = screenPoint[2];
                        }
                    }
                }
                else {
                    if (mNewPath) {
                        List<HitResult> results = frame.hitTest(mLastX, mLastY);
                        for (HitResult result : results) {
                            Pose pose = result.getHitPose();
                            mNewPath = false;
                            mRenderer.addPath(pose.tx(), pose.ty(), pose.tz());
                            mLastPoint[0] = pose.tx();
                            mLastPoint[1] = pose.ty();
                            mLastPoint[2] = pose.tz();
                            break;
                        }
                    } else if (mPointAdded) {
                        List<HitResult> results = frame.hitTest(mLastX, mLastY);
                        for (HitResult result : results) {
                            Pose pose = result.getHitPose();
                            if (checkDistance(pose.tx(), pose.ty(), pose.tz(),
                                    mLastPoint[0], mLastPoint[1], mLastPoint[2])) {
                                mRenderer.addPoint(pose.tx(), pose.ty(), pose.tz());
                                mLastPoint[0] = pose.tx();
                                mLastPoint[1] = pose.ty();
                                mLastPoint[2] = pose.tz();
                                break;
                            }
                        }
                        mPointAdded = false;
                    }
                }
            }
        });
        mSurfaceView.setPreserveEGLContextOnPause(true);
        mSurfaceView.setEGLContextClientVersion(2);
        mSurfaceView.setRenderer(mRenderer);
        mSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    @Override
    protected void onPause() {
        super.onPause();

        mSurfaceView.onPause();
        mSession.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        requestCameraPermission();

        try {
            if (mSession == null) {
                switch (ArCoreApk.getInstance().requestInstall(this, mUserRequestedInstall)) {
                    case INSTALLED:
                        mSession = new Session(this);
                        Log.d(TAG, "ARCore Session created.");
                        break;
                    case INSTALL_REQUESTED:
                        mUserRequestedInstall = false;
                        Log.d(TAG, "ARCore should be installed.");
                        break;
                }
            }
        }
        catch (UnsupportedOperationException e) {
            Log.e(TAG, e.getMessage());
        }

        mConfig = new Config(mSession);
        if (!mSession.isSupported(mConfig)) {
            Log.d(TAG, "This device is not support ARCore.");
        }
        mSession.configure(mConfig);
        mSession.resume();

        mSurfaceView.onResume();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mLastX = event.getX();
        mLastY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mNewPath = true;
                mPointAdded = true;
                break;
            case MotionEvent.ACTION_MOVE:
                mPointAdded = true;
                break;
            case MotionEvent.ACTION_UP:
                mPointAdded = false;
                break;
        }
        return true;
    }

    public void onUndoButtonClick(View view) {
        mRenderer.removePath();
    }

    public float[] getScreenPoint(float x, float y, float w, float h,
                                    float[] projMat, float[] viewMat) {
        float[] position = new float[3];
        float[] direction = new float[3];

        x = x * 2 / w - 1.0f;
        y = (h - y) * 2 / h - 1.0f;

        float[] viewProjMat = new float[16];
        Matrix.multiplyMM(viewProjMat, 0, projMat, 0, viewMat, 0);

        float[] invertedMat = new float[16];
        Matrix.setIdentityM(invertedMat, 0);
        Matrix.invertM(invertedMat, 0, viewProjMat, 0);

        float[] farScreenPoint = new float[]{x, y, 1.0F, 1.0F};
        float[] nearScreenPoint = new float[]{x, y, -1.0F, 1.0F};
        float[] nearPlanePoint = new float[4];
        float[] farPlanePoint = new float[4];

        Matrix.multiplyMV(nearPlanePoint, 0, invertedMat, 0, nearScreenPoint, 0);
        Matrix.multiplyMV(farPlanePoint, 0, invertedMat, 0, farScreenPoint, 0);

        position[0] = nearPlanePoint[0] / nearPlanePoint[3];
        position[1] = nearPlanePoint[1] / nearPlanePoint[3];
        position[2] = nearPlanePoint[2] / nearPlanePoint[3];

        direction[0] = farPlanePoint[0] / farPlanePoint[3] - position[0];
        direction[1] = farPlanePoint[1] / farPlanePoint[3] - position[1];
        direction[2] = farPlanePoint[2] / farPlanePoint[3] - position[2];

        normalize(direction);

        position[0] += (direction[0] * 0.1f);
        position[1] += (direction[1] * 0.1f);
        position[2] += (direction[2] * 0.1f);

        return position;
    }

    private void normalize(float[] v) {
        double norm = Math.sqrt(v[0] * v[0] + v[1] * v[1] + v[2] * v[2]);
        v[0] /= norm;
        v[1] /= norm;
        v[2] /= norm;
    }

    public boolean checkDistance(float x1, float y1, float z1, float x2, float y2, float z2) {
        float x = x1 - x2;
        float y = y1 - y2;
        float z = z1 - z2;
        return (Math.sqrt(x*x + y*y + z*z) > MIN_DISTANCE);
    }

    private void requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, 0);
        }
    }

    private void hideStatusBarAndTitleBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
