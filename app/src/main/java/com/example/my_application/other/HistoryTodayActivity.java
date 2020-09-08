package com.example.my_application.other;

import android.graphics.Color;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.bh.ldp.lib_base.basemvvm.BaseListActivityM;
import com.bh.ldp.lib_base.view.RecyclerViewHolder;
import com.example.my_application.R;
import com.zzhoujay.richtext.CacheType;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.RichText;
import bean.HistoryTodayBean;

public class HistoryTodayActivity extends BaseListActivityM<HistoryTodayBean.ContentBean> {

    private HistoryTodayViewModel historyTodayViewModel;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_constellation;
    }

    @Override
    protected void onLoadMore() {

    }

    @Override
    protected void onRefresh() {
        historyTodayViewModel.requestData();
    }

    @Override
    protected void bindItemView(RecyclerViewHolder holder, int position, HistoryTodayBean.ContentBean item) {
        holder.setText(R.id.title_tv, item.getTitle())
                .setText(R.id.date_tv, item.getYear() + "年" + item.getMonth() + "月" + item.getDay() + "日");
        TextView contentTv = holder.getView(R.id.text);
        RichText.from(item.getContent())
                .autoFix(true)
                .autoPlay(false)
                .showBorder(true)
                .singleLoad(false)
                .borderColor(Color.WHITE)
                .borderRadius(6)
                .borderSize(6)
                .scaleType(ImageHolder.ScaleType.fit_auto)
                .cache(CacheType.all)
                .into(contentTv);
    }


    @Override
    protected int getItemLayoutRes() {
        return R.layout.item_history_today;
    }

    @Override
    protected void initData() {
        historyTodayViewModel = ViewModelProviders.of(this).get(HistoryTodayViewModel.class);
        historyTodayViewModel.getData().observe(this, new Observer<HistoryTodayBean>() {
            @Override
            public void onChanged(HistoryTodayBean historyTodayBean) {
                loadDataRefresh(historyTodayBean.getResult());
            }
        });
        historyTodayViewModel.requestData();
        RichText.initCacheDir(this);
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("历史上的今天");
    }

    @Override
    public boolean isLoadMore() {
        return false;
    }

    @Override
    public boolean isRefresh() {
        return true;
    }

    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return super.getItemDecoration();
    }
}
