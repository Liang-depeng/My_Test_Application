package com.example.my_application.details;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.bh.ldp.lib_base.basev.ViewHolder;
import com.bh.ldp.lib_base.view.MyBaseAdapter;
import com.example.my_application.R;

import java.util.List;

import bean.CaiDetailsBean;

/**
 * @author mini
 * @date 2019/10/16
 */
public class CaiDetailsProcessAdapter extends MyBaseAdapter<CaiDetailsBean.ProcessBean> {

    protected CaiDetailsProcessAdapter(Context context, List<CaiDetailsBean.ProcessBean> dataList) {
        super(context, dataList);
    }

    @Override
    protected void bindView(ViewHolder viewHolder, CaiDetailsBean.ProcessBean item, int position, View convertView, ViewGroup parent) {
        viewHolder.setText(R.id.process_tv, "步骤" + (position+1) + ": "+item.getPcontent())
                .setImagineByNetUrl(mContext, R.id.process_iv, item.getPic());
    }

    @Override
    protected int getItemLayoutRes() {
        return R.layout.item_process;
    }
}
