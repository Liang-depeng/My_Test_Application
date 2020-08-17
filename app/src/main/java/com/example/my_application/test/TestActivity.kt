package com.example.my_application.test

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.example.my_application.R
import com.example.my_application.caipu.CaiPuActivity
import com.example.my_application.other.ConstellationActivity
import com.example.my_application.other.PhoneActivity
import com.example.my_application.train.TrainActivity

import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity(), MyIntentService.ReceiveListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
//        button.visibility = View.GONE
//        button.setOnClickListener {
//            val intent = Intent("ldp.example.com.android_demo.studydemo.broadcastReceiver2")
//            if (Build.VERSION.SDK_INT >= 26)
//            intent.component = ComponentName("ldp.example.com.android_demo.studydemo.views","ldp.example.com.android_demo.studydemo.views.MyBroadcastReceiver2")
//            sendBroadcast(intent)
//        }
        initView()
    }

    private fun initView() {
        cai_ay.setOnClickListener {
            startActivity(Intent(this, CaiPuActivity::class.java))
        }
        train_ay.setOnClickListener {
            startActivity(Intent(this, TrainActivity::class.java))
        }
        phone_ay.setOnClickListener {
            startActivity(Intent(this, PhoneActivity::class.java))
        }
        constellation_ay.setOnClickListener {
            startActivity(Intent(this, ConstellationActivity::class.java))
        }
        share_test.setOnClickListener {
            doShare()
        }

        MyIntentService.setListener(this)
        service_test.setOnClickListener {
            for (i in 0..5) {
                val intent = Intent(this, MyIntentService::class.java)
                intent.putExtra("name", "tony$i")
                startService(intent)
            }

        }
    }

    private val SHARE_REQUEST_CODE = 12312

    private fun doShare() {
        val intent = Intent()
        // 01 分享一段文字
//        intent.setAction(Intent.ACTION_SEND).setType("text/plain")
//            .putExtra(Intent.EXTRA_TEXT, "这是分享的文字")

        // 02 分享一张图片
        val uri = Uri.parse("android.resource://" + applicationContext.packageName + "/" + R.mipmap.ic_load_failed_1)
        intent.setAction(Intent.ACTION_SEND).setType("image/*")
            .putExtra(Intent.EXTRA_STREAM,uri)

        Intent.createChooser(intent, "这是一个选择框")
        startActivityForResult(intent, SHARE_REQUEST_CODE)
    }

    private val handler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message?) {

            service_test_tv.text = msg?.obj as String

        }
    }

    override fun setStr(s: String?) {

        val message = Message.obtain()
        message.obj = s
        handler.sendMessageDelayed(message, 2000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val receiveIntent = data
    }

}
