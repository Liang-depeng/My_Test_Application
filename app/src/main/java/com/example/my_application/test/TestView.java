package com.example.my_application.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TestView extends FrameLayout {

    public TestView(@NonNull Context context) {
        super(context);
    }

    public TestView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 事件分发
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 是否拦截
     * @param event
     * @return
     */
    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return true;
    }

    /**
     * 拦截之后事件处理
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Toast.makeText(getContext(), "center", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
