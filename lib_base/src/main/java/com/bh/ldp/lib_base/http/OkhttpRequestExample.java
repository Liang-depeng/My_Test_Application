package com.bh.ldp.lib_base.http;

import android.content.Context;
import android.util.Log;

import com.bh.ldp.lib_base.BaseApplication;
import com.bh.ldp.lib_base.utils.AppUtils;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author dapeng
 * @date 2019/10/15 伪代码
 */
public class OkhttpRequestExample {

    private OkHttpClient mHttpClient;

    private void startRequest() {
        //  notes 1、创建OkHttpClient对象
        mHttpClient = new OkHttpClient().newBuilder().build();

        //  notes POST请求 需要创建 RequestBody
        RequestBody body = new FormBody.Builder()
                .add("params1", "value1")
                .add("params2", "value2")
                .build();

        // notes  2、创建Request对象
        Request request = new Request.Builder()
                .url("url")
                .post(body) // POST 请求 添加
                .get() // get 请求
                .build();

        // notes 3、构建Call对象
        final Call call = mHttpClient.newCall(request);

        // notes 4、发起请求

        // 4.1 异步请求
        {
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    // 子线程

                    //响应行
                    Log.e("result",response.protocol() +"  "+response.code() + "  " +response.message());

                    Headers headers = response.headers();
                    for (int i=0;i<headers.size();i++){
                        Log.e("result",headers.name(i) + " " + headers.value(i));
                    }

                    // 响应体（数据）
                    String result = response.body().string();

                }
            });
        }

        // 4.2 同步请求
        {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        Response response = call.execute(); // 必须在子线程
                        String result = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }

        // 超时设置
        {

            Context context = BaseApplication.getAppContent();

            mHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(6, TimeUnit.SECONDS)
                    .readTimeout(6, TimeUnit.SECONDS)
                    .writeTimeout(6, TimeUnit.SECONDS)
                    .cache(new Cache(new File(context.getCacheDir(), "Cache"), 10 * 1024 * 1024))
                    .build();
        }

        // POST表单提交
        {
            RequestBody requestBody = new FormBody.Builder()
                    .add("params1", "value1")
                    .add("params2", "value2")
                    .build();
        }

        // 请求头设置
        {
            Request request1 = new Request.Builder().url("url")
                    .addHeader("type", "value")
                    .header("type", "value")
                    .build();
        }

    }
}
