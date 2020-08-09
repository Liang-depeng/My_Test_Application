package com.bh.ldp.lib_base.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;


/**
 * Created by zhouyi on 2015/4/23.
 */
public abstract class DialogBase {

    protected AlertDialog dialog;

    protected View window;

    public AlertDialog getDialog() {
        return dialog;
    }

    public DialogBase(Context context, int resId) {//默认是直接显示的

        this(context,resId,true);
    }

    public DialogBase(Context context, int resId, boolean show) {

        dialog = new AlertDialog.Builder(context).create();

        window = LayoutInflater.from(context).inflate(resId, null);

        dialog.setView(window);

        dialog.setCancelable(false);

        if(show){
            dialog.show();
        }

    }

    public void show() {
        if (dialog != null)
            dialog.show();
    }

    /**
     * 消除dialog
     */
    public void dismiss() {
        if (dialog.isShowing())
            dialog.dismiss();
    }

}
