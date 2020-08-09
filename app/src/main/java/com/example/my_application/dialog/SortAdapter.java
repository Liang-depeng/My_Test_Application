package com.example.my_application.dialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.bh.ldp.lib_base.basev.ViewHolder;
import com.bh.ldp.lib_base.view.MyBaseAdapter;
import com.example.my_application.R;


import java.util.List;

import bean.SortListBean;

/**
 * @author mini
 * @date 2019/10/16
 */
public class SortAdapter extends MyBaseAdapter<SortListBean.ResultBean> {

    private onItemClickListener listener;

    protected SortAdapter(Context context, List<SortListBean.ResultBean> dataList) {
        super(context, dataList);
    }

    @Override
    protected void bindView(ViewHolder viewHolder, final SortListBean.ResultBean item, int position, View convertView, ViewGroup parent) {
        viewHolder.setText(R.id.sort_txt, item.getName())
                .setOnClickListener(R.id.sort_txt, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.updateList(item.getList());
                    }
                });
    }

    @Override
    protected int getItemLayoutRes() {
        return R.layout.item_sort;
    }

    public interface onItemClickListener {
        void updateList(List<SortListBean.ResultBean.TypeBean> list);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }
}
