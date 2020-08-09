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
public class CaiDetailsMaterialAdapter extends MyBaseAdapter<CaiDetailsBean.MaterialBean> {


    protected CaiDetailsMaterialAdapter(Context context, List<CaiDetailsBean.MaterialBean> dataList) {
        super(context, dataList);
    }

    @Override
    protected void bindView(ViewHolder viewHolder, CaiDetailsBean.MaterialBean item, int position, View convertView, ViewGroup parent) {
        viewHolder.setText(R.id.material_type, item.getMname() + ": ")
                .setText(R.id.material_name, item.getAmount());
    }

    @Override
    protected int getItemLayoutRes() {
        return R.layout.item_material;
    }
}
