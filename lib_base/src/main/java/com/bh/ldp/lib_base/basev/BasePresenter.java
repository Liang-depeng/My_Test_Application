package com.bh.ldp.lib_base.basev;

/**
 * created by Da Peng
 */
public abstract class BasePresenter<V extends BaseView> implements BasePresenterImpl {

    protected V mView = null;

    public BasePresenter(V mView) {
        this.mView = mView;
    }

    @Override
    public void onDetachView() {
        mView = null;
    }
}
