package com.example.my_application.other

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import bean.PhoneBean
import com.bh.ldp.lib_base.basemvvm.BaseActivityM
import com.bh.ldp.lib_base.utils.AppUtils
import com.example.my_application.R
import kotlinx.android.synthetic.main.activity_phone.*


class PhoneActivity : BaseActivityM() {

    private lateinit var mViewModel: PhoneViewModel

    override fun getLayoutResId(): Int {
        return R.layout.activity_phone
    }

    override fun initData() {
        mViewModel = ViewModelProviders.of(this)[PhoneViewModel::class.java]
        mViewModel.phoneData.observe(this, object : Observer<PhoneBean> {
            override fun onChanged(bean: PhoneBean?) {
                bindData(bean)
            }
        })
        mViewModel.errorMsg.observe(this, Observer<String> {
            showToastCenter(it)
        })
    }

    private fun bindData(bean: PhoneBean?) {
        bean?.result?.let {
            phone_number.text = it.shouji
            phone_province.text = it.province
            phone_city.text = it.city
            phone_company.text = it.company
            phone_type.text = if (it.cardtype.isNullOrEmpty()) "暂无数据~" else it.cardtype
            phone_quhao.text = it.areacode
        }
        result_layout.visibility = View.VISIBLE
    }

    override fun initView() {
        showBackIv(true)
        setTitle("查询电话号码")
        result_layout.visibility = View.GONE
        query.setOnClickListener {
            if (!AppUtils.etContentIsEmpty(phone_et))
                mViewModel.requestData(phone_et.text.toString().trim())
        }
    }
}
