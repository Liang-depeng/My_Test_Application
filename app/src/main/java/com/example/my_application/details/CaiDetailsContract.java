package com.example.my_application.details;

import com.bh.ldp.lib_base.basev.BaseView;

/**
 * created by Da Peng at 2019/10/12
 */
public interface CaiDetailsContract {

    public interface View extends BaseView {

    }

    public interface Presenter {

        void requestData();

    }

}
