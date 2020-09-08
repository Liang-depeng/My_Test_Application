package com.example.my_application.caipu;

import androidx.annotation.NonNull;

import com.bh.ldp.lib_base.basev.BasePresenter;
import com.bh.ldp.lib_base.http.HttpHelper;
import com.bh.ldp.lib_base.http.HttpUrl;
import com.bh.ldp.lib_base.http.OnHttpResponseListner;
import com.bh.ldp.lib_base.http.RequestParams;

import bean.CaiPuBean;
import bean.SortListBean;

/**
 * created by Da Peng
 */
public class CaiPuPresenter extends BasePresenter<CaiPuContract.View> implements CaiPuContract.Presenter, OnHttpResponseListner {

    private HttpHelper mHttpHelper = HttpHelper.getInstance();

    public CaiPuPresenter(CaiPuContract.View mView) {
        super(mView);
        mHttpHelper.setCallBack(this);
    }

    @Override
    public void requestData(String keyword, int page) {
        mView.showLoadingDialog();
        RequestParams params = new RequestParams();
        params.setNetUrl(HttpUrl.CAI_URL);
        params.setParseClass(CaiPuBean.class);
        params.put("keyword", keyword)
                .put("start", (page - 1) * 20)
                .put("num", page * 20)
                .setTag("bysearch");
        mHttpHelper.startRequest(params);
    }


    @Override
    public void requestSortListData() {
        mView.showLoadingDialog();
        RequestParams params = new RequestParams();
        params.setNetUrl(HttpUrl.CAI_SORT_LIST);
        params.setParseClass(SortListBean.class);
        mHttpHelper.startRequest(params);
    }

    @Override
    public void requestSortData(int id, int page) {
        mView.showLoadingDialog();
        RequestParams params = new RequestParams();
        params.setNetUrl(HttpUrl.CAI_SORT_DETAILS_QUERY);
        params.setParseClass(CaiPuBean.class);
        params.put("classid", id)
                .put("start", (page - 1) * 20)
                .put("num", page * 20)
                .setTag("byclassid");

        mHttpHelper.startRequest(params);
    }


    @Override
    public void onSuccess(RequestParams requestParams, @NonNull Object obj) {
        if (obj instanceof CaiPuBean) {
            mView.success((CaiPuBean) obj, requestParams);
        } else if (obj instanceof SortListBean) {
            mView.initDialog(((SortListBean) obj));
        }
        mView.hideLoadingDialog();

    }

    @Override
    public void onFailed(RequestParams requestParams, @NonNull Object obj) {
        mView.failed(obj, requestParams);
        mView.hideLoadingDialog();
    }
}
