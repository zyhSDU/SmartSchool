package com.example.administrator.smartschool.temp

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast

/**
 * Created by Administrator on 2018/9/8 0008.
 */

object Temp{
    fun startThread(init: () -> Unit) {
        object : Thread() {
            override fun run() {
                init()
            }
        }.start()
    }
    @SuppressLint("WrongConstant", "ShowToast")
    fun showToast(context: Context, text: String?) {
        Toast.makeText(context, text, 0).show()
    }
}
