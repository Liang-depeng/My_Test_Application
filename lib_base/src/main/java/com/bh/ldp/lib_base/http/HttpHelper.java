package com.bh.ldp.lib_base.http;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.bh.ldp.lib_base.Contants;
import com.bh.ldp.lib_base.utils.AppUtils;
import com.bh.ldp.lib_base.utils.LogUtils;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * created by Da Peng at 2019/10/12
 */
public class HttpHelper {

    private OkHttpClient okHttpClient;
    private OnHttpResponseListner listener;
    private Handler mHandler;

    private HttpHelper() {
        this.mHandler = new Handler(Looper.getMainLooper());
        this.okHttpClient = new OkHttpClient();
    }

    public static HttpHelper getInstance() {
        return HttpHolder.INSTANCE;
    }

    public void setCallBack(OnHttpResponseListner listener){
        this.listener = listener;
    }

    private static class HttpHolder {
        private static final HttpHelper INSTANCE = new HttpHelper();
    }

    /**
     * OkHttp3 发送异步请求
     *
     * @param requestParams requestParams
     */
    public void startRequest(final RequestParams requestParams) {

        // 第一步获取okHttpClient对象
       // okHttpClient = new OkHttpClient();

        // 第二步构建Request对象
        Request request = new Request.Builder()
                .post(requestParams.getRequestBody())
                .url(requestParams.getNetUrl())
                .build();

        StringBuilder urls = new StringBuilder();
        urls.append(request.url());
        FormBody body = (FormBody) requestParams.getRequestBody();

        for (int i = 0; i < body.size(); i++) {
            urls.append("&");
            urls.append(body.encodedName(i)).append("=").append(body.encodedValue(i));
        }

        Log.e("httpUrl", "\n" + urls);

        //第三步构建Call对象
        Call call = okHttpClient.newCall(request);
        //第四步:异步post/get请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

                e.printStackTrace();

                LogUtils.e("okhttp",call.toString()+"  "+e.getLocalizedMessage()+e.getMessage());

                // 请求失败
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onFailed(requestParams, "请求失败");
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try {
                    // 子线程
                    String result = "";

                    if (response.body() != null) {
                        result = response.body().string();
                    }

                    Log.e("httpUrl", result);

//                    //  Value of type java.lang.String cannot be converted to JSONObject
//                    //  在确认JSON数据格式没有问题的前提下，100%是因为UTF-8的BOM头
//
//                    if (result.startsWith("\\ufeff")) {
//                        result = result.substring(1);
//                    }

                    final JSONObject jsonObject = new JSONObject(result);

                    // 处理数据
                    response(jsonObject, requestParams);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void response(final JSONObject jsonObject, final RequestParams requestParams) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    if (TextUtils.equals(jsonObject.optString("result"), "")) {
                        requestSuccessNoDataError(requestParams, jsonObject);
                    } else {
                        requestSuccess(requestParams, jsonObject);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 请求成功
     */
    private void requestSuccess(RequestParams requestParams, JSONObject jsonObject) {
        if (requestParams.getParseClass() != null) {
            Object object = new Gson().fromJson(jsonObject.toString(), requestParams.getParseClass());
            listener.onSuccess(requestParams, object);
        } else {
            listener.onSuccess(requestParams, jsonObject);
        }
    }

    /**
     * 请求错误 没有数据
     *
     * @param requestParams 请求参数
     * @param jsonObject    json数据
     */
    private void requestSuccessNoDataError(RequestParams requestParams, JSONObject jsonObject) {
        if ("".equals(jsonObject.optString("msg"))) {
            listener.onFailed(requestParams, Contants.NODATA);
        } else {
            listener.onFailed(requestParams, jsonObject.optString("msg"));
        }
    }


    public void testAsyncTaskHttpRequest(final String requestUrl, final Class genericClass) {
        HttpAsyncTaskTest httpAsyncTaskTest = new HttpAsyncTaskTest();
        httpAsyncTaskTest.execute(requestUrl, (Object) genericClass);
    }

    /**
     * 利用AsyncTask 实现异步网络请求 HttpURLConnection
     */
    public class HttpAsyncTaskTest extends AsyncTask<Object, Integer, Object> {

        // 方法1：onPreExecute（）
        // 作用：执行 线程任务前的操作
        // 注：根据需求复写
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        // 方法2：doInBackground（）
        // 作用：接收输入参数、执行任务中的耗时操作、返回 线程任务执行的结果
        // 注：必须复写，从而自定义线程任务
        @Override
        protected Object doInBackground(Object... objects) {
            LogUtils.e("thread", "doInBackground + " + AppUtils.getThreadInfo());
            StringBuilder result = new StringBuilder();
            HttpURLConnection connection = null;
            try {
                URL url = new URL(objects[0].toString());
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                //connection.addRequestProperty("key","value"); post
                connection.setReadTimeout(8000);
                connection.setConnectTimeout(8000);
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                //StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (connection != null)
                    connection.disconnect();
            }
            LogUtils.e("obj", objects[0].toString() + "  " + objects[1].toString());

            final Object object = new Gson().fromJson(result.toString(), ((Class) objects[1]));
            return object;
        }

        // 方法3：onProgressUpdate（）
        // 作用：在主线程 显示线程任务执行的进度
        // 注：根据需求复写

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        // 方法4：onPostExecute（）
        // 作用：接收线程任务执行结果、将执行结果显示到UI组件
        // 注：必须复写，从而自定义UI操作

        @Override
        protected void onPostExecute(Object object) {
            listener.onSuccess(null, object);
        }

        // 方法5：onCancelled()
        // 作用：将异步任务设置为：取消状态

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }


}
