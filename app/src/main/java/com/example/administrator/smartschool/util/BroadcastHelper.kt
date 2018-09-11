package com.example.administrator.smartschool.util

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

/**
 * Created by Administrator on 2018/9/10 0010.
 */

class BroadcastHelper {
    var receiver: BroadcastReceiver? = null
    var activity: Activity? = null
    var canUnRegister: Boolean = false
    fun registerReceiver(activity: Activity, string: String, initOnReceive: (context: Context, intent: Intent) -> Unit) {
        if (canUnRegister)
            return
        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                initOnReceive(context, intent)
            }
        }
        activity.registerReceiver(receiver, IntentFilter(string))
        this.activity = activity
        canUnRegister = true
    }

    fun unregisterReceiver() {
        if (canUnRegister) {
            activity!!.unregisterReceiver(receiver!!)
        }
    }
}
