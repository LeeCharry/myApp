package com.example.jack.myapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.kotlin_test_activity.*

/**
 * Created by lcy on 2018/10/15.
 */
class KotlinTestActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kotlin_test_activity)

        initview()
        button.text = "发送消息"
    }

    private fun initview() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return
    }
}