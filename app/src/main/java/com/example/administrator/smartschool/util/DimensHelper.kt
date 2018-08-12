package com.example.administrator.smartschool.util

import android.app.Activity
import android.util.DisplayMetrics

/**
 * Created by Administrator on 2018/7/16 0016.
 */

object DimensHelper{
     fun getPixelsFromDP(activity: Activity, size: Int): Int {
        val metrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metrics)
        return size * metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT
    }
}
