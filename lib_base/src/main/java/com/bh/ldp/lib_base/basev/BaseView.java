package com.bh.ldp.lib_base.basev;

/**
 * created by Da Peng at 2019/6/21
 */
public interface BaseView {

    void showProgressDialog();

    void hideProgressDialog();

    void showLongToast(String message);

    void showShortToast(String message);

    void showBackIv(boolean isShow);

    void setTitle(String title);

//    void startRequestInfo();

}
