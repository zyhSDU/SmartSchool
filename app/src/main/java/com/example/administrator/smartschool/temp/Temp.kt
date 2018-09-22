package com.example.administrator.smartschool.temp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * Created by Administrator on 2018/9/8 0008.
 */

object Temp{
    @SuppressLint("WrongConstant", "ShowToast")
    fun showToast(context: Context, text: String?) {
        Toast.makeText(context, text, 0).show()
    }

    fun start(context: Context,clazz: Class<*>,string: String) {
        val starter = Intent(context, clazz)
        starter.putExtra("string",string)
        context.startActivity(starter)
    }
}
