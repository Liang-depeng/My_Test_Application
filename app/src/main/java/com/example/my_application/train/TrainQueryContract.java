package com.example.my_application.train;

import com.bh.ldp.lib_base.basev.BaseView;
import bean.TrainBean;

/**
 * @author mini
 * @date 2019/10/21
 */
public class TrainQueryContract {

    public interface View extends BaseView {
        void loadData(TrainBean bean);

        void showErrorMsg(String msg);
    }

    public interface Presenter {
        void requestData(String start, String end, int isHighTrain, String date);
    }
}
