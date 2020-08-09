package com.bh.ldp.lib_base.basev;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.bh.ldp.lib_base.R;

import androidx.annotation.NonNull;

/**
 * @author mini
 * @date 2019/10/16
 */
public abstract class BaseDialog extends Dialog {

    protected Context content;

    public BaseDialog(@NonNull Context context) {
        super(context);
        this.content = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(content).inflate(getLayoutResId(), null, false);
        bindView(ViewHolder.init(view),this);
        setContentView(view);
    }

    protected abstract void bindView(ViewHolder holder, BaseDialog dialog);

    protected abstract int getLayoutResId();

    @Override
    public void show() {
        super.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
