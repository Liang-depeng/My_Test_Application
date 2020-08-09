package com.bh.ldp.lib_base.basev;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bh.ldp.lib_base.R;


/**
 * @author Da Peng
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {

    public final String TAG = getClass().getSimpleName();
    private ProgressDialog mProgressDialog;
    protected P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        initPresenter();
        initData();
        initView();
    }

    protected abstract void initPresenter();

    protected void initView() {
        showBackIv(true);
        setTitle("");
    }

    protected abstract void initData();

    protected abstract int getLayoutResId();

    @Override
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("加载中...");
        }
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showLongToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public void showShortToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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

    @Override
    public void showBackIv(boolean isShow) {
        if (!judgeHasTitleLayout())
            return;
        ViewGroup view = getContentView().findViewById(R.id.back_iv);
        if (isShow) {
            view.setVisibility(View.VISIBLE);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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

    private boolean judgeHasTitleLayout() {
        if (getContentView().findViewById(R.id.back_iv) == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDetachView();
        }
    }
}
