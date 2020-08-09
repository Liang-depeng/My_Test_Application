package com.example.my_application.other;

import androidx.lifecycle.MutableLiveData;

import com.bh.ldp.lib_base.basemvvm.BaseViewModel;
import com.bh.ldp.lib_base.http.HttpUrl;
import com.bh.ldp.lib_base.http.RequestParams;

import java.util.ArrayList;

import bean.ConstellationBean;

public class ConstellationViewModel extends BaseViewModel {

    private MutableLiveData<ArrayList<ConstellationBean.ResultBean>> data = new MutableLiveData<>();

    public MutableLiveData<ArrayList<ConstellationBean.ResultBean>> getData() {
        return data;
    }

    public void requestData() {
        RequestParams params = new RequestParams();
        params.setNetUrl(HttpUrl.CONSTELLATION_QUERY);
        params.setParseClass(ConstellationBean.class);
        mHttpHelper.startRequest(params);
    }

    @Override
    protected void requestSuccess(RequestParams requestParams, Object obj) {
        ConstellationBean bean = (ConstellationBean) obj;
        data.setValue(bean.getResult());
    }
}
