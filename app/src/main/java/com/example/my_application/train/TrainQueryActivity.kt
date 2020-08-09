package com.example.my_application.train

import android.annotation.SuppressLint
import bean.TrainBean
import bean.TrainDetailBean
import com.bh.ldp.lib_base.basev.BaseListActivity
import com.bh.ldp.lib_base.view.RecyclerViewHolder
import com.example.my_application.R


/**
 * @author mini
 * @date 2019/10/18
 */
class TrainQueryActivity : BaseListActivity<TrainDetailBean, TrainQueryPresenter>(), TrainQueryContract.View {

    private var start = ""
    private var end = ""
    private var ishigh = 0
    private var date = ""

    override fun getLayoutResId(): Int {
        return R.layout.activity_train_query
    }

    override fun onLoadMore() {}

    override fun onRefresh() {
        presenter.requestData(start, end, ishigh, date)
    }

    override fun bindItemView(holder: RecyclerViewHolder, position: Int, item: TrainDetailBean) {
        holder.apply {
            setText(R.id.start_station, item.station)
            setText(R.id.end_station, item.endstation)
            setText(R.id.costTime, item.costtime)
            setText(R.id.start_time, item.departuretime)
            setText(R.id.end_time, item.arrivaltime)
            setText(R.id.trainType, item.trainno + "  " + item.typename)
            setText(R.id.isEnd, if (item.isend == 1) "终点站" else "过路站")
            setText(R.id.distance, item.distance.toString() + "km")
        }
    }

    override fun getItemLayoutRes(): Int {
        return R.layout.item_tarin
    }

    override fun initPresenter() {
        presenter = TrainQueryPresenter(this)
    }

    override fun initData() {
        start = intent.getStringExtra("start")
        end = intent.getStringExtra("end")
        date = intent.getStringExtra("date")
        ishigh = intent.getIntExtra("ishigh", 0)
        onRefresh()
    }

    @SuppressLint("SimpleDateFormat")
    override fun initView() {
        super.initView()
    }


    override fun isLoadMore(): Boolean {
        return false
    }

    override fun isRefresh(): Boolean {
        return false
    }

    override fun loadData(bean: TrainBean) {
        loadDataRefresh(bean.result.list)
    }

    override fun showErrorMsg(msg: String?) {
        showShortToast(msg)
    }

}
