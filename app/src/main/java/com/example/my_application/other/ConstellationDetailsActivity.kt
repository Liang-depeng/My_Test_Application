package com.example.my_application.other

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import bean.ConstellationDetailBean
import com.bh.ldp.lib_base.basemvvm.BaseActivityM
import com.bumptech.glide.Glide
import com.example.my_application.R
import kotlinx.android.synthetic.main.activity_constellation_details.*

class ConstellationDetailsActivity : BaseActivityM() {

    private lateinit var mViewModel: ConstellationDetailsViewModel
    private var starId = 0
    private var name = ""
    private var imagUrl = ""

    override fun getLayoutResId(): Int {
        return R.layout.activity_constellation_details
    }

    override fun initData() {
        starId = intent.getIntExtra("astroid", 0)
        name = intent.getStringExtra("starname")
        imagUrl = intent.getStringExtra("imageUrl")

        Glide.with(this).load(imagUrl).into(image_star)
        name_star.text = name

        mViewModel = ViewModelProviders.of(this)[ConstellationDetailsViewModel::class.java]
        mViewModel.data.observe(this, Observer<ConstellationDetailBean> {
            refreshView(it)
        })
        mViewModel.queryData(starId, "2019/10/28")
    }

    private fun refreshView(bean: ConstellationDetailBean?) {
        bean?.result?.let {
            summary_tv.text = it.today.presummary
            my_star.text = it.today.star
            my_color.text = it.today.color
            my_number.text = it.today.number
            zonghe_star.rating = it.today.summary.toFloat()
            caiyun_star.rating = it.today.money.toFloat()
            work_star.rating = it.today.career.toFloat()
            love_star.rating = it.today.love.toFloat()
            health_star.rating = it.today.health.toFloat()

            summary_tv_tomorrow.text = it.tomorrow.presummary
            my_star_tomorrow.text = it.tomorrow.star
            my_color_tomorrow.text = it.tomorrow.color
            my_number_tomorrow.text = it.tomorrow.number
            zonghe_star_tomorrow.rating = it.tomorrow.summary.toFloat()
            caiyun_star_tomorrow.rating = it.tomorrow.money.toFloat()
            work_star_tomorrow.rating = it.tomorrow.career.toFloat()
            love_star_tomorrow.rating = it.tomorrow.love.toFloat()
            health_star_tomorrow.rating = it.tomorrow.health.toFloat()

            week_love_tv.text = it.week.love
            week_career_tv.text = it.week.career
            week_money_tv.text = it.week.money
            week_health_tv.text = it.week.health

            month_content_tv.text = it.month.summary
            month_love_tv.text = it.month.love
            month_money_tv.text = it.month.money
            month_career_tv.text = it.month.career
            month_health_tv.text = it.month.health

            year_content_tv.text = it.year.summary
            year_love_tv.text = it.year.love
            year_career_tv.text = it.year.career
            year_money_tv.text = it.year.money

        }
    }

    override fun initView() {
        super.initView()
        setTitle("详情")
    }

}
