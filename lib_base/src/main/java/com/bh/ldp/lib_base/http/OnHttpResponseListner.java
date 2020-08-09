package com.bh.ldp.lib_base.http;

import org.json.JSONObject;

import androidx.annotation.NonNull;

/**
 * created by Da Peng at 2019/10/12
 */
public interface OnHttpResponseListner {

    void onSuccess(RequestParams requestParams, @NonNull Object obj);

    void onFailed(RequestParams requestParams, @NonNull Object obj);

}
