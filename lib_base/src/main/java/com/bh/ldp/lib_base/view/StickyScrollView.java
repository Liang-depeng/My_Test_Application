package com.bh.ldp.lib_base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

import com.bh.ldp.lib_base.R;
import com.bh.ldp.lib_base.utils.LogUtils;

public class StickyScrollView extends FrameLayout implements NestedScrollView.OnScrollChangeListener{

    private LinearLayout contentView;
    private NestedScrollView nestScrollView;

    public StickyScrollView(@NonNull Context context) {
        this(context, null);
    }

    public StickyScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StickyScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.ui_sticky_scroll_view_layout, this, true);
        contentView = findViewById(R.id.content_view);
        nestScrollView = findViewById(R.id.nest_scroll_view);
        nestScrollView.setOnScrollChangeListener(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void init() {
        int childCount = getChildCount();
        LogUtils.e("StickyView", String.valueOf(childCount));

    }

    public void setStickyViews(int... childStickyViewPosition) {
        int[] stickyViewPosition = childStickyViewPosition;

    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

    }
}
