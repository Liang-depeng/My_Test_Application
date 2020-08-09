package com.example.my_application.train;

import androidx.annotation.NonNull;

import com.bh.ldp.lib_base.basev.BasePresenter;
import com.bh.ldp.lib_base.http.HttpHelper;
import com.bh.ldp.lib_base.http.HttpUrl;
import com.bh.ldp.lib_base.http.OnHttpResponseListner;
import com.bh.ldp.lib_base.http.RequestParams;

import bean.TrainBean;

/**
 * @author mini
 * @date 2019/10/21
 */
public class TrainQueryPresenter extends BasePresenter<TrainQueryContract.View> implements TrainQueryContract.Presenter, OnHttpResponseListner {

    private HttpHelper mHttpHelper = HttpHelper.getInstance();

    public TrainQueryPresenter(TrainQueryContract.View mView) {
        super(mView);
        mHttpHelper.setCallBack(this);
    }

    @Override
    public void requestData(String start, String end, int isHighTrain, String date) {
        mView.showProgressDialog();
        RequestParams requestParams = new RequestParams();
        requestParams.setNetUrl(HttpUrl.TRAIN_QUERY);
        requestParams.setParseClass(TrainBean.class);
        requestParams.put("start", start);
        requestParams.put("end", end);
        requestParams.put("ishigh", isHighTrain);
        requestParams.put("date", date);
        mHttpHelper.startRequest(requestParams);
    }

    @Override
    public void onSuccess(RequestParams requestParams, @NonNull Object obj) {
        mView.hideProgressDialog();
        mView.loadData(((TrainBean) obj));
    }

    @Override
    public void onFailed(RequestParams requestParams, @NonNull Object obj) {
        mView.hideProgressDialog();
        mView.showErrorMsg(((String) obj));
    }
}
