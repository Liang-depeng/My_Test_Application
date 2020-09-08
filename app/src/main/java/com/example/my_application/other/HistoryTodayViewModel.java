package com.example.my_application.other;

import androidx.lifecycle.MutableLiveData;

import com.bh.ldp.lib_base.basemvvm.BaseViewModel;
import com.bh.ldp.lib_base.http.HttpUrl;
import com.bh.ldp.lib_base.http.RequestParams;

import java.util.Calendar;

import bean.HistoryTodayBean;

public class HistoryTodayViewModel extends BaseViewModel {

    private MutableLiveData<HistoryTodayBean> data = new MutableLiveData<>();

    public MutableLiveData<HistoryTodayBean> getData() {
        return data;
    }

    public void requestData() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        RequestParams params = new RequestParams();
        params.setNetUrl(HttpUrl.HISTORY_TODAY_QUERY);
        params.setParseClass(HistoryTodayBean.class);
        params.put("month", month).put("day", day);

        mHttpHelper.startRequest(params);
    }

    @Override
    protected void requestSuccess(RequestParams requestParams, Object obj) {
        if (obj instanceof HistoryTodayBean) {
            data.setValue(((HistoryTodayBean) obj));
        }
    }
}
