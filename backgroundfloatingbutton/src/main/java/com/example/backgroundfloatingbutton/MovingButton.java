package com.example.backgroundfloatingbutton;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class MovingButton {

    private Context m_Context;
    private Handler m_Handler;
    private WindowManager.LayoutParams mParams;
    private WindowManager floatingWindowManager;
    private View view;
    private ImageView exitButton;
    private float START_X;
    private float START_Y;
    private int PREV_X;
    private int PREV_Y;

    public MovingButton(Context context, Handler handler) {
        m_Context = context;
        m_Handler = handler;

        initView();
    }

    private WindowManager.LayoutParams getFloatingParam() {
        mParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                PixelFormat.TRANSLUCENT);
        mParams.gravity = Gravity.LEFT | Gravity.TOP;

        return mParams;
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) m_Context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.exit_button_layout, null);
        exitButton = (ImageView) view.findViewById(R.id.exit_button);

        floatingWindowManager = (WindowManager) m_Context.getSystemService(Context.WINDOW_SERVICE);
        floatingWindowManager.addView(view, getFloatingParam());
        view.setOnClickListener(ButtonClickListener);
        view.setOnTouchListener(mViewTouchListener);
        floatingWindowManager.updateViewLayout(view, mParams);
    }

    private View.OnClickListener ButtonClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
//            m_Handler.sendEmptyMessage(FloatingService.EXIT);
        }
    };


    private View.OnTouchListener mViewTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int x, y;

            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    START_X = event.getRawX();
                    START_Y = event.getRawY();
                    PREV_X = mParams.x;
                    PREV_Y = mParams.y;
                    break;
                case MotionEvent.ACTION_MOVE:
                    x = (int) (event.getRawX() - START_X);
                    y = (int) (event.getRawY() - START_Y);
                    mParams.x = PREV_X + x;
                    mParams.y = PREV_Y + y;
                    floatingWindowManager.updateViewLayout(view, mParams);
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                    x = (int) (event.getRawX() - START_X);
                    y = (int) (event.getRawY() - START_Y);
                    break;
            }
            return true;
        }
    };

}
