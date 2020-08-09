package com.bh.ldp.lib_base.basev;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bh.ldp.lib_base.R;
import com.bh.ldp.lib_base.view.BaseRecyclerViewAdapter;
import com.bh.ldp.lib_base.view.RecyclerViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mini
 * @date 2019/10/17
 */
public abstract class BaseListActivity<T,E extends BasePresenter> extends BaseActivity<E> implements SmartRefreshListener {

    private List<T> mList = new ArrayList<>();
    private int currentPage = 1;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView recyclerView;
    private SmartRefreshLayout mRefreshLayout;
    private View emptyView;
    private boolean isLoadMore = false;
    private boolean isPullRefresh = false;

    @Override
    protected void initView() {
        super.initView();
        initRecyclerView();
        initRefreshLayout();
        emptyView = findViewById(R.id.emptyView);
    }

    private void initRefreshLayout() {
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRefreshLayout.setEnableLoadMore(isLoadMore());
        mRefreshLayout.setEnableRefresh(isRefresh());
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                currentPage = 1;
                BaseListActivity.this.onRefresh();
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                BaseListActivity.this.onLoadMore();
            }
        });
    }

    public void loadDataRefresh(ArrayList<T> list) {
        if (recyclerView == null) {
            return;
        }
        if (1 == currentPage) {
            if (list != null && list.size() > 0) {
                mList.clear();
                mList.addAll(list);
            }
        } else {
            if (list != null && list.size() > 0) {
                mList.addAll(list);
            }
        }
        mAdapter.notifyDataSetChanged();
        refreshUi(list);
        currentPage++;
    }

    private void refreshUi(ArrayList<T> list) {
        if (list == null) {
            refreshIsSuccess(false);
        } else {
            refreshIsSuccess(true);
        }
        showEmptyView(mList.size() == 0);
    }

    private void showEmptyView(boolean show) {
        if (show) {
            mRefreshLayout.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            mRefreshLayout.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }

    private void refreshIsSuccess(boolean success) {
        mRefreshLayout.finishRefresh(success);
        mRefreshLayout.finishLoadMore(success);
    }

    private void initRecyclerView() {
        mAdapter = getAdapter();//上海
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(getLayoutManger());
        recyclerView.addItemDecoration(getItemDecoration());
        recyclerView.setAdapter(mAdapter);
    }

    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
    }

    protected RecyclerView.Adapter getAdapter() {
        return new ListAdapter(this, mList);
    }

    protected RecyclerView.LayoutManager getLayoutManger() {
        return new LinearLayoutManager(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.base_list_activity;
    }

    private class ListAdapter extends BaseRecyclerViewAdapter<T> {

        public ListAdapter(Context context, List<T> list) {
            super(context, list);
        }

        @Override
        protected int getItemLayoutResId() {
            return getItemLayoutRes();
        }

        @Override
        protected void bindItemViewHolder(RecyclerViewHolder holder, int position, T item) {
            bindItemView(holder, position, item);
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }
    }

    protected abstract void onLoadMore();

    protected abstract void onRefresh();

    protected abstract void bindItemView(RecyclerViewHolder holder, int position, T item);

    protected abstract int getItemLayoutRes();

}
