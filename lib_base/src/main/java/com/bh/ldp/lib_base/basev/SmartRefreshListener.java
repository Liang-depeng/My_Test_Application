package com.bh.ldp.lib_base.basev;

/**
 * @author mini
 * @date 2019/10/21
 */
public interface SmartRefreshListener {

    /**
     * @return 是否开启下拉刷新
     */
    boolean isLoadMore();

    /**
     * @return 是否开启上拉加载
     */
    boolean isRefresh();

}
