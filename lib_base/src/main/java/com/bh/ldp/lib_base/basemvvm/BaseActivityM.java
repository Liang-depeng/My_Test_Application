package com.bh.ldp.lib_base.basemvvm;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bh.ldp.lib_base.R;
import com.bh.ldp.lib_base.basev.BaseView;

public abstract class BaseActivityM extends AppCompatActivity implements BaseView {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        initData();
        initView();
    }

    protected abstract int getLayoutResId();

    protected void initView() {
        showBackIv(true);
        setTitle("");
    }

    protected abstract void initData();

    @Override
    public void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("正在加载中...");
        }
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showLongToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showShortToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showToastCenter(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public void showBackIv(boolean isShow) {
        if (!judgeHasTitleLayout()) {
            return;
        }
        ViewGroup view = getContentView().findViewById(R.id.back_iv);
        if (isShow) {
            view.setVisibility(View.VISIBLE);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        } else {
            view.setVisibility(View.GONE);
        }
    }

    @Override
    public void setTitle(String title) {
        if (judgeHasTitleLayout()) {
            ((TextView) getContentView().findViewById(R.id.title_tv)).setText(title);
        }
    }

    /**
     * 方法1: Activity.getWindow().getDecorView().findViewById(android.R.id.content)
     * 方法2: Activity.findViewById(android.R.id.content)
     * 方法3: Activity.findViewById(android.R.id.content).getRootView()
     *
     * @return contentView
     */
    public View getContentView() {
        return (ViewGroup) getWindow().getDecorView();
    }

    private boolean judgeHasTitleLayout() {
        if (getContentView().findViewById(R.id.back_iv) == null) {
            return false;
        } else {
            return true;
        }
    }
}
