package com.example.my_application.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.GridView;

import com.bh.ldp.lib_base.view.DialogBase;
import com.example.my_application.R;


import java.util.List;

import bean.SortListBean;

/**
 * @author mini
 * @date 2019/10/16
 */
public class ChooseDialog extends DialogBase {

    private SortAdapter mSortAdapter;
    private SortDetailsAdapter mSortDetailsAdapter;
    private GridView gridView;

    public ChooseDialog(final Context context, List<SortListBean.ResultBean> list) {
        super(context, R.layout.dialog_choose_layout, false);
        mSortAdapter = new SortAdapter(context, list);
        mSortAdapter.setOnItemClickListener(new SortAdapter.onItemClickListener() {
            @Override
            public void updateList(List<SortListBean.ResultBean.TypeBean> list) {
                mSortDetailsAdapter = new SortDetailsAdapter(context, list);
                mSortDetailsAdapter.setOnItemClickListener(new SortDetailsAdapter.onItemClickListener() {
                    @Override
                    public void setCheckedId(int caiId) {
                        listener.setCaiId(caiId);
                    }
                });
                gridView.setAdapter(mSortDetailsAdapter);
            }
        });

        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                gridView.setAdapter(mSortAdapter);
            }
        });

        gridView = (GridView) window.findViewById(R.id.gridView);
        gridView.setAdapter(mSortAdapter);

        window.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                dialog.dismiss();
            }
        });

        window.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private onItemClickListener listener;

    public interface onItemClickListener {
        void setCaiId(int caiId);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }
}
