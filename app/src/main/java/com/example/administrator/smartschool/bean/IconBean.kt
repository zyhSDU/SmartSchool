package com.example.administrator.smartschool.bean

import com.example.administrator.smartschool.R

/**
 * Created by Administrator on 2018/9/1 0001.
 */

class IconBean(
        val imageViewId: Int= R.drawable.ic_launcher,
        val string: String,
        val onClick:()->Unit={}
)
