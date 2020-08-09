package com.example.my_application.caipu

import android.text.TextUtils
import androidx.recyclerview.widget.LinearLayoutManager
import bean.CaiDetailsBean
import bean.CaiPuBean
import bean.SortListBean
import com.bh.ldp.lib_base.basev.BaseActivity
import com.bh.ldp.lib_base.http.RequestParams
import com.bh.ldp.lib_base.utils.AppUtils
import com.example.my_application.R
import com.example.my_application.dialog.ChooseDialog
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.activity_cai_pu.*

class CaiPuActivity : BaseActivity<CaiPuPresenter>(), CaiPuContract.View {

    private val list = arrayListOf<CaiDetailsBean>()
    private lateinit var chooseDialog: ChooseDialog
    private var tag = ""
    private var keyword = "白菜"
    private var page = 1
    private val adapter by lazy {
        CaiPuAdapter(this, list)
    }

    override fun initPresenter() {
        presenter = CaiPuPresenter(this)
    }

    override fun initView() {
        super.initView()
        showBackIv(false)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        refreshLayout.setOnRefreshListener(object : OnRefreshListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                page = 1
                request()
            }
        })

        refreshLayout.setOnLoadMoreListener(object : OnLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                request()
            }
        })

        more_btn.setOnClickListener {
            presenter.requestSortListData()
        }
        search.setOnClickListener {
            if (!AppUtils.etContentIsEmpty(edit_query)) {
                page = 1
                keyword = edit_query.text.toString().trim()
                presenter.requestData(keyword, page)
            }
        }
    }

    private fun request() {
        if (TextUtils.equals(tag, "bysearch")) {
            presenter.requestData(keyword, page)
        } else if (TextUtils.equals(tag, "byclassid")) {
            presenter.requestSortData(caiId, page)
        }
    }

    override fun initData() {
        showProgressDialog()
        presenter.requestData(keyword, page)

    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_cai_pu
    }

    private var caiId = 0

    override fun initDialog(bean: SortListBean?) {
        chooseDialog = ChooseDialog(this, bean?.result)
        chooseDialog.setOnItemClickListener {
            page = 1
            caiId = it
            presenter.requestSortData(it, page)
            chooseDialog.dismiss()
        }
        chooseDialog.show()
    }

    override fun success(bean: CaiPuBean, requestParams: RequestParams) {
        this.tag = requestParams.tag as String
        refreshLayout.finishRefresh(500)
        refreshLayout.finishLoadMore(500)
        hideProgressDialog()

        loadData(bean)
    }

    private fun loadData(bean: CaiPuBean) {
        if (page == 1) {
            list.clear()
            list.addAll(bean.result.list)
        } else {
            list.addAll(bean.result.list)
        }
        page++
        adapter.notifyDataSetChanged()
    }

    override fun failed(obj: Any?, requestParams: RequestParams) {
        refreshLayout.finishRefresh(false)
        refreshLayout.finishLoadMore(false)
        hideProgressDialog()
        showLongToast(obj.toString())
    }

    override fun showLoadingDialog() {
        showProgressDialog()
    }

    override fun hideLoadingDialog() {
        hideProgressDialog()
    }
}
