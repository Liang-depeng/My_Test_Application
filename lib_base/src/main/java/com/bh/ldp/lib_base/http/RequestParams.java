package com.bh.ldp.lib_base.http;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * @author dapeng
 * @date 2019/10/15
 */
public class RequestParams extends BaseRequest{

    private FormBody.Builder formBody;

    public RequestParams() {
        formBody = new FormBody.Builder();
    }

    public RequestBody getRequestBody() {
        return formBody.build();
    }

    public RequestParams put(String key, String value) {
        formBody.add(key, value);
        return this;
    }

    public RequestParams put(String key, int value) {
        formBody.add(key, String.valueOf(value));
        return this;
    }

    public RequestParams put(String key, float value) {
        formBody.add(key, String.valueOf(value));
        return this;
    }

    public RequestParams put(String key, double value) {
        formBody.add(key, String.valueOf(value));
        return this;
    }

    public RequestParams put(String key, boolean value) {
        formBody.add(key, String.valueOf(value));
        return this;
    }

    public RequestParams put(String key, long value) {
        formBody.add(key, String.valueOf(value));
        return this;
    }
}
