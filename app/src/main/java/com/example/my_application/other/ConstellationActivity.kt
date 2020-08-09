package com.example.my_application.other

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import bean.ConstellationBean
import com.bh.ldp.lib_base.basemvvm.BaseListActivityM
import com.bh.ldp.lib_base.view.RecyclerViewHolder
import com.example.my_application.R


class ConstellationActivity : BaseListActivityM<ConstellationBean.ResultBean>() {

    private lateinit var mViewModel: ConstellationViewModel

    override fun getLayoutResId(): Int {
        return R.layout.activity_constellation
    }

    override fun initView() {
        super.initView()
        setTitle("星座运势")
    }

    override fun onLoadMore() {
    }

    override fun onRefresh() {
        mViewModel.requestData()
    }

    override fun initData() {
        mViewModel = ViewModelProviders.of(this)[ConstellationViewModel::class.java]
        mViewModel.data.observe(this, Observer<ArrayList<ConstellationBean.ResultBean>> {
            loadDataRefresh(it)
        })
        mViewModel.errorMsg.observe(this, Observer<String> {
            showToastCenter(it)
        })
        mViewModel.requestData()
    }

    override fun getItemLayoutRes(): Int {
        return R.layout.item_constellation
    }

    override fun isRefresh(): Boolean {
        return true
    }

    override fun bindItemView(holder: RecyclerViewHolder?, position: Int, item: ConstellationBean.ResultBean?) {
        holder?.apply {
            setImagineByNetUrl(this@ConstellationActivity, R.id.stars_image, item?.pic)
            setText(R.id.name, item?.astroname)
            setText(R.id.date, item?.date)
        }

        holder?.itemView?.setOnClickListener {
            startActivity(Intent(this, ConstellationDetailsActivity::class.java)
                    .putExtra("astroid", item?.astroid)
                    .putExtra("starname", item?.astroname)
                    .putExtra("imageUrl", item?.pic))
        }
    }

    override fun isLoadMore(): Boolean {
        return false
    }


}
