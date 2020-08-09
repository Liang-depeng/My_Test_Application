package com.bh.ldp.lib_base.basev;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bh.ldp.lib_base.R;


/**
 * created by Da Peng at 2019/6/21
 */
public abstract class BaseFragment extends Fragment implements BaseView {

    private ProgressDialog mProgressDialog;
    private View contentView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = getLayoutResId(inflater);
        return contentView;
    }


    private boolean judgeHasTitleLayout() {
        if (contentView.findViewById(R.id.back_iv) == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPresenter();
        initData();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    protected abstract void initView();

    protected abstract void initPresenter();

    protected abstract void initData();

    protected abstract View getLayoutResId(LayoutInflater inflater);

    @Override
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
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
    public void showShortToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showBackIv(boolean isShow) {
        if (!judgeHasTitleLayout())
            return;
        ViewGroup view = contentView.findViewById(R.id.back_iv);
        if (isShow) {
            view.setVisibility(View.VISIBLE);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getActivity() != null) {
                        getActivity().finish();
                    }
                }
            });
        } else {
            view.setVisibility(View.GONE);
        }
    }

    @Override
    public void setTitle(String title) {
        if (judgeHasTitleLayout()) {
            ((TextView) contentView.findViewById(R.id.title_tv)).setText(title);
        }
    }


    @Override
    public void showLongToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
