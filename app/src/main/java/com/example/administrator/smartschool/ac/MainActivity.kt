package com.example.administrator.smartschool.ac

import com.ab.activity.AbActivity
import com.ab.view.titlebar.AbTitleBar
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.util.AbTitleBarHelper
import com.example.administrator.smartschool.util.Utils

/**
 * Created by Administrator on 2018/7/16 0016.
 */

class MainActivity : BaseAbAc() {
    override val layoutResId: Int
        get() = R.layout.ac_main

    override fun initOnCreate() {
        initAbTitleBar(mAbTitleBar, this)
        initPermission()
    }

    private fun initAbTitleBar(abTitleBar: AbTitleBar, abActivity: AbActivity) {
        AbTitleBarHelper.initAbTitleBar(
                abTitleBar,
                abActivity.javaClass.simpleName,
                R.color.black,
                R.drawable.ic_arrow_back_white_24dp,
                16, 0, 0, 0)
        abTitleBar.setLogoOnClickListener {
            abActivity.finish()
        }
    }

    private fun initPermission() {
        Utils.dynamicPermission(
                this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }
}
