package com.example.my_application.caipu;

import com.bh.ldp.lib_base.basev.BaseView;
import com.bh.ldp.lib_base.http.RequestParams;

import bean.CaiPuBean;
import bean.SortListBean;

/**
 * created by Da Peng at 2019/10/12
 */
public class CaiPuContract {

    public interface View extends BaseView {

        void success(CaiPuBean obj, RequestParams requestParams);

        void initDialog(SortListBean bean);

        void failed(Object obj, RequestParams requestParams);

        void showLoadingDialog();

        void hideLoadingDialog();


    }

    public interface Presenter {

        void requestData(String keyword, int num);

        void requestSortListData();

        void requestSortData(int id, int page);

    }

}
