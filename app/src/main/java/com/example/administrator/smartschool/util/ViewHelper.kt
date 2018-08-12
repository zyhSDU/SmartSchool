package com.example.administrator.smartschool.util

import android.view.View
import android.widget.LinearLayout

/**
 * Created by Administrator on 2018/7/18 0018.
 */

object ViewHelper {
    fun setMargins(view: View, left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) {
        val layoutParams = view.layoutParams as LinearLayout.LayoutParams
        layoutParams.setMargins(left, top, right, bottom)
    }

    fun setSizes(view: View, height: Int, width: Int) {
        val layoutParams = view.layoutParams
        layoutParams.height = height
        layoutParams.width = width
    }
}
