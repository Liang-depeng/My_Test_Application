package com.example.my_application.details

import android.view.LayoutInflater
import bean.CaiDetailsBean
import com.bh.ldp.lib_base.basev.BaseActivity
import com.bh.ldp.lib_base.utils.ViewUtils
import com.bumptech.glide.Glide
import com.example.my_application.R
import kotlinx.android.synthetic.main.activity_cai_details.*
import kotlinx.android.synthetic.main.list_head_view.*
import kotlinx.android.synthetic.main.list_head_view.view.*

class CaiDetailsActivity : BaseActivity<CaiDetailsPresenter>() {

    private var data: CaiDetailsBean? = null
    private val caiPuProcessAdapter by lazy {
        CaiDetailsProcessAdapter(this, data?.process)
    }
    private val caiPuMaterialAdapter by lazy {
        CaiDetailsMaterialAdapter(this, data?.material)
    }

    override fun initPresenter() {
    }

    override fun initView() {
        super.initView()
        val headView = LayoutInflater.from(this).inflate(R.layout.list_head_view, null, false)
        headView.headListView.adapter = caiPuMaterialAdapter
        //解决lisview嵌套问题
        ViewUtils.setListViewHeightBasedOnChildren(headView.headListView)
        listView.addHeaderView(headView)
        Glide.with(this)
                .load(data?.pic)
                .placeholder(R.mipmap.ic_load_failed_1)
                .into(headView.cai_details_iv)
        listView.adapter = caiPuProcessAdapter

        eat_num.text = data?.peoplenum
        prepare_time.text = data?.preparetime
        cooking_time.text = data?.cookingtime
        content_tv.text = data?.content
        type_tv.text = data?.tag
        cai_name.text = data?.name
    }

    override fun initData() {
        data = intent.getSerializableExtra("details") as CaiDetailsBean
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_cai_details
    }

}
