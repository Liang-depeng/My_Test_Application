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
public class SortDetailsAdapter extends MyBaseAdapter<SortListBean.ResultBean.TypeBean> {

    private onItemClickListener listener;

    protected SortDetailsAdapter(Context context, List<SortListBean.ResultBean.TypeBean> dataList) {
        super(context, dataList);
    }

    @Override
    protected void bindView(ViewHolder viewHolder, final SortListBean.ResultBean.TypeBean item, int position, View convertView, ViewGroup parent) {
        viewHolder.setText(R.id.sort_txt, item.getName())
        .setOnClickListener(R.id.sort_txt, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setCheckedId(item.getClassid());
            }
        });
    }

    @Override
    protected int getItemLayoutRes() {
        return R.layout.item_sort;
    }

    public interface onItemClickListener {
        void setCheckedId(int caiId);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }
}
