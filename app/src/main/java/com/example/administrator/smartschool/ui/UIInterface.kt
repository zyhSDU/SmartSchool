package com.example.administrator.smartschool.ui

import android.app.Activity
import android.content.Intent

interface UIInterface {
    fun startActivity(activity: Activity, clazz: Class<*>) {
        activity.startActivity(Intent(activity, clazz))
    }

    fun startThread(init: () -> Unit) {
        object : Thread() {
            override fun run() {
                init()
            }
        }.start()
    }
}