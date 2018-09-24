package com.example.administrator.smartschool.bean

import android.app.Activity
import android.content.Intent
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.ui.UIInterface

/**
 * Created by Administrator on 2018/9/1 0001.
 */

class IconBean(
        var imageViewId: Int = R.drawable.ic_launcher,
        val string: String,
        var onClick: () -> Unit = {}
) : UIInterface {
    constructor (imageViewId: Int = R.drawable.ic_launcher,string: String, activity: Activity, clazz: Class<*>) : this(string = string) {
        this.imageViewId=imageViewId
        this.onClick = {
            val intent =Intent(activity,clazz)
            intent.putExtra("titleBarTitle",string)
            activity.startActivity(intent)
        }
    }
}
