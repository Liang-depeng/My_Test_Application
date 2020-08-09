package com.example.my_application.train

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.my_application.R
import kotlinx.android.synthetic.main.activity_train.*
import java.text.SimpleDateFormat
import java.util.*

class TrainActivity : AppCompatActivity() {

    private var start = ""
    private var end = ""
    private var ishigh = 0
    private var date = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_train)
        initView()
    }

    @SuppressLint("SimpleDateFormat")
    private fun initView() {// 北京
        val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd")// HH:mm:ss
        val date1 = Date(System.currentTimeMillis())
        date = simpleDateFormat.format(date1)
        date_tv.text = date
        query_btn.setOnClickListener {
            getUserData()
            startActivity(Intent(this, TrainQueryActivity::class.java)
                    .putExtra("start", start)
                    .putExtra("end", end)
                    .putExtra("date", date)
                    .putExtra("ishigh", ishigh))
        }
        dateChoose.setOnClickListener { showDatePickerDialog() }
        findViewById<FrameLayout>(R.id.back_iv).setOnClickListener { finish() }
    }

    private fun getUserData() {
        start = start_et.text.toString().trim()
        end = end_et.text.toString().trim()
        date = date_tv.text.toString()
        ishigh = if (highCheckBox.isChecked) 1 else 0
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
            /**
             * @param year the initially selected year
             * @param month the initially selected month (0-11 for compatibility with
             *              {@link Calendar#MONTH})
             * @param dayOfMonth the initially selected day of month (1-31, depending
             *                   on month)
             */
            view, year, month, dayOfMonth ->

            date = year.toString() + "/" + (month + 1).toString() + "/" + dayOfMonth.toString()
            date_tv.text = date
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        datePickerDialog.show()
    }
}
