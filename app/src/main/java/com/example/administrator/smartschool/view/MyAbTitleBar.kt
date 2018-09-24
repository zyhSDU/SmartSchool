package com.example.administrator.smartschool.view

import android.content.Context
import android.util.AttributeSet
import android.widget.Button

import com.ab.view.titlebar.AbTitleBar

/**
 * Created by Administrator on 2018/9/24 0024.
 */

class MyAbTitleBar : AbTitleBar {
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context) : super(context) {}

    fun getTitleTextBtn():Button{
        return titleTextBtn
    }
}
