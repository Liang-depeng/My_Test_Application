package com.bh.ldp.lib_base.basemvvm;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bh.ldp.lib_base.http.HttpHelper;
import com.bh.ldp.lib_base.http.OnHttpResponseListner;
import com.bh.ldp.lib_base.http.RequestParams;

public abstract class BaseViewModel extends ViewModel implements OnHttpResponseListner {

    protected HttpHelper mHttpHelper;
    protected MutableLiveData<String> errorMsg = new MutableLiveData<>();

    public BaseViewModel(){
        mHttpHelper = HttpHelper.getInstance();
        mHttpHelper.setCallBack(this);
    }

    public MutableLiveData<String> getErrorMsg() {
        return errorMsg;
    }

    @Override
    public void onSuccess(RequestParams requestParams, @NonNull Object obj) {
        requestSuccess(requestParams, obj);
    }

    @Override
    public void onFailed(RequestParams requestParams, @NonNull Object obj) {
        errorMsg.setValue(((String) obj));
    }

    protected abstract void requestSuccess(RequestParams requestParams, Object obj);

}
