package com.example.studyanimation.opengl.surfacetexture;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by palnak on 2/8/2017.
 */

public class MyGLSurfaceView extends GLSurfaceView {

    MyGL20Renderer renderer;
    public MyGLSurfaceView(Context context)
    {
        super(context);

        setEGLContextClientVersion(2);
        setEGLConfigChooser(8,8,8,8,16,0);
        setZOrderOnTop(true);


        renderer = new MyGL20Renderer((MainCameraGLActivity)context);

        setRenderer(renderer);

        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);//means "do not call onDrawFrame() unless something explicitly requests rendering with requestRender()

    }

    public MyGL20Renderer getRenderer()
    {
        return renderer;
    }
}
