package com.example.administrator.smartschool.bean

import android.app.Activity
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.ui.UIInterface

/**
 * Created by Administrator on 2018/9/1 0001.
 */

class IconBean(
        val imageViewId: Int = R.drawable.ic_launcher,
        val string: String,
        var onClick: () -> Unit = {}
) : UIInterface {
    constructor (string: String, activity: Activity, clazz: Class<*>) : this(string = string) {
        this.onClick = {
            startActivity(activity, clazz)
        }
    }
}
