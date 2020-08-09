package com.example.my_application.other;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.bh.ldp.lib_base.basemvvm.BaseViewModel;
import com.bh.ldp.lib_base.http.HttpUrl;
import com.bh.ldp.lib_base.http.RequestParams;

import bean.PhoneBean;

public class PhoneViewModel extends BaseViewModel {

    private MutableLiveData<PhoneBean> phoneData = new MutableLiveData<>();

    public void requestData(String phoneNumber) {
        RequestParams params = new RequestParams();
        params.setNetUrl(HttpUrl.PHONE_QUERY);
        params.setParseClass(PhoneBean.class);
        params.put("shouji", phoneNumber);
        mHttpHelper.startRequest(params);
    }

    public MutableLiveData<PhoneBean> getPhoneData() {
        return phoneData;
    }

    @Override
    protected void requestSuccess(RequestParams requestParams, Object obj) {
        PhoneBean phoneBean = (PhoneBean) obj;
        phoneData.setValue(phoneBean);
    }

    @Override
    public void onFailed(RequestParams requestParams, @NonNull Object obj) {
        super.onFailed(requestParams, obj);
    }
}
