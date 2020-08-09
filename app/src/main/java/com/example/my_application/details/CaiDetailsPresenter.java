package com.example.my_application.details;

import com.bh.ldp.lib_base.basev.BasePresenter;
import com.bh.ldp.lib_base.http.HttpUrl;
import com.bh.ldp.lib_base.http.RequestParams;

/**
 * created by Da Peng at 2019/10/12
 */
public class CaiDetailsPresenter extends BasePresenter<CaiDetailsContract.View> implements CaiDetailsContract.Presenter{

    public CaiDetailsPresenter(CaiDetailsContract.View mView) {
        super(mView);
    }

    @Override
    public void requestData() {
//        RequestParams params = new RequestParams();
//        params.setNetUrl(HttpUrl.CAI_DETAILS_QUERY);
    }
}
