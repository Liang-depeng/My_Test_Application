package com.bh.ldp.lib_base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.bh.ldp.lib_base.R;
import com.bh.ldp.lib_base.utils.DeviceUtil;
import com.bh.ldp.lib_base.utils.LogUtils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导图
 */
public class SplashGuideView extends FrameLayout implements ViewPager.OnPageChangeListener {

    private String TAG = SplashGuideView.class.getSimpleName();
    private ViewPager viewPager;
    private Context context;
    private LinearLayout indicatorLayout;
    private List<View> pagerItemViews = new ArrayList<>();
    private List<View> indicatorViews = new ArrayList<>();
    private List<?> imageUrls = new ArrayList<>();
    private int defaultUnSelectDrawableResId = R.drawable.ui_welcome_unselect;
    private int defaultSelectDrawableResId = R.drawable.ui_welcome_select;

    public SplashGuideView(@NonNull Context context) {
        this(context, null);
    }

    public SplashGuideView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SplashGuideView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.ui_splash_guide_view_layout, this, true);
        viewPager = view.findViewById(R.id.view_pager);
        indicatorLayout = view.findViewById(R.id.indicator_layout);
    }

    private void initIndicator() {
        indicatorViews.clear();

        if (imageUrls.size() <= 1) {
            indicatorLayout.setVisibility(GONE);
            return;
        }

        indicatorLayout.setVisibility(VISIBLE);

        for (int i = 0; i < imageUrls.size(); i++) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(defaultUnSelectDrawableResId);
            indicatorViews.add(imageView);

            indicatorLayout.addView(imageView);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            layoutParams.leftMargin = DeviceUtil.dip2px(context, 10);
            imageView.setLayoutParams(layoutParams);
        }
        ((ImageView) indicatorViews.get(0)).setImageResource(defaultSelectDrawableResId);
    }

    private void initImages() {
        pagerItemViews.clear();

        for (int i = 0; i < imageUrls.size(); i++) {
            if (imageUrls.size() - 1 == i) {
                View view = LayoutInflater.from(context).inflate(R.layout.ui_welcome_show_prepare_layout, null, false);
                ImageView imageView = (ImageView) view.findViewById(R.id.image);
                view.findViewById(R.id.button).setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 进入 主activity
                        if (callback != null) {
                            callback.showNextActivity();
                        }
                    }
                });
                Glide.with(context).load(imageUrls.get(i)).into(imageView);
                pagerItemViews.add(view);
                break;
            }

            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(context).load(imageUrls.get(i)).into(imageView);
            pagerItemViews.add(imageView);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        LogUtils.e(TAG, position + "  " + positionOffset + "  " + positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {

        if (indicatorViews.size() > 1) {
            for (int i = 0; i < indicatorViews.size(); i++) {
                ((ImageView) indicatorViews.get(i)).setImageResource(i == position ? defaultSelectDrawableResId : defaultUnSelectDrawableResId);
            }
            indicatorLayout.setVisibility(position == indicatorViews.size() - 1 ? GONE : VISIBLE);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class ViewPagerAdapter extends PagerAdapter {

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(((View) object));
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(pagerItemViews.get(position));
            View view = pagerItemViews.get(position);
            return view;
        }

        @Override
        public int getCount() {
            return pagerItemViews.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }

    public SplashGuideView setSelectDrawableResId(int selectDrawableResId) {
        this.defaultSelectDrawableResId = selectDrawableResId;
        return this;
    }

    public SplashGuideView setDefaultUnSelectDrawableResId(int unSelectDrawableResId) {
        this.defaultUnSelectDrawableResId = unSelectDrawableResId;
        return this;
    }

    public SplashGuideView setImageUrls(List<?> imageUrls) {
        this.imageUrls = imageUrls;
        return this;
    }

    public void show() {
        if (imageUrls == null || imageUrls.size() == 0) return;
        initImages();
        initIndicator();
        viewPager.setAdapter(new ViewPagerAdapter());
        viewPager.addOnPageChangeListener(this);
    }

    public SplashGuideView setShowMainActivityCallback(Callback callback) {
        this.callback = callback;
        return this;
    }

    private Callback callback;

    public interface Callback {

        void showNextActivity();
    }

}
