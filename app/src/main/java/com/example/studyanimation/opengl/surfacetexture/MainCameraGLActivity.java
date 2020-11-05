package com.example.studyanimation.opengl.surfacetexture;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainCameraGLActivity extends AppCompatActivity implements SurfaceTexture.OnFrameAvailableListener {

    private Camera mCamera;
    private MyGLSurfaceView glSurfaceView;
    private SurfaceTexture surface;
    private MyGL20Renderer renderer;
    private SurfaceHolder mSurfaceHolder;
    private SurfaceView msurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        glSurfaceView = new MyGLSurfaceView(this);

        renderer =glSurfaceView.getRenderer();


        setContentView(glSurfaceView);
        //msurfaceView=(SurfaceView) findViewById(R.id.surface);
        //setContentView(renderer_view);
    }


    public void startCamera(int texture)
    {
        surface = new SurfaceTexture(texture);
        surface.setOnFrameAvailableListener(this);
        renderer.setSurface(surface);

        mCamera = Camera.open();


        try
        {
            mCamera.setPreviewTexture(surface);
            mCamera.startPreview();
        }
        catch (IOException ioe)
        {
            Log.w("MainActivity","CAM LAUNCH FAILED");
        }
    }

    @Override
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        glSurfaceView.requestRender();

    }


    @Override
    public void onPause() {
        super.onPause();
        mCamera.stopPreview();
        mCamera.release();
        System.exit(0);
    }
}
