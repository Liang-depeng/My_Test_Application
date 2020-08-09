package com.example.my_application.test

import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.my_application.R
import kotlinx.android.synthetic.main.activity_test2.*

class Test2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test2)
        initView()
    }

    private fun initView() {
        in11.setOnClickListener {
            Toast.makeText(this,"in111",Toast.LENGTH_SHORT).show()
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }
}
