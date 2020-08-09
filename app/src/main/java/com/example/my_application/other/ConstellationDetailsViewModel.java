package com.example.my_application.other;

import androidx.lifecycle.MutableLiveData;

import com.bh.ldp.lib_base.basemvvm.BaseViewModel;
import com.bh.ldp.lib_base.http.HttpUrl;
import com.bh.ldp.lib_base.http.RequestParams;

import bean.ConstellationDetailBean;

public class ConstellationDetailsViewModel extends BaseViewModel {

    private MutableLiveData<ConstellationDetailBean> data = new MutableLiveData<>();

    public MutableLiveData<ConstellationDetailBean> getData() {
        return data;
    }


    public void queryData(int astroid, String date) {
        RequestParams params = new RequestParams();
        params.setNetUrl(HttpUrl.CONTELLATION_DETAILS_QUERY);
        params.put("astroid", astroid);
        params.put("date", date);
        params.setParseClass(ConstellationDetailBean.class);
        mHttpHelper.startRequest(params);
    }

    @Override
    protected void requestSuccess(RequestParams requestParams, Object obj) {
        data.setValue(((ConstellationDetailBean) obj));
    }
}
