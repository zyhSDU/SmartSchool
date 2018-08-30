package com.example.administrator.smartschool.ac.abac

/**
 * Created by Administrator on 2018/3/23 0023.
 */
import android.content.Intent
import android.os.Bundle
import com.ab.activity.AbActivity
import com.example.administrator.smartschool.util.Utils
import com.example.administrator.smartschool.util.AbTitleBarHelper

abstract class BaseAbAc : AbActivity(),UiOperation {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAbContentView(layoutResId)
        Utils.setAllOnClickListener(this, this)//Activity的根View
        AbTitleBarHelper.hideAbTitleBar(mAbTitleBar)()
        initOnCreate()
    }

    protected fun startActivity(clazz: Class<*>) {
        startActivity(Intent(this,clazz))
    }

    protected fun startThread(init:()-> Unit) {
        object : Thread() {
            override fun run() {
                init()
            }
        }.start()
    }
}