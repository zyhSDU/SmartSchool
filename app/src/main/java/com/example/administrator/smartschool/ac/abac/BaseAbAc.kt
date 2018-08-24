package com.example.administrator.smartschool.ac.abac

/**
 * Created by Administrator on 2018/3/23 0023.
 */
import android.os.Bundle
import android.view.View
import com.ab.activity.AbActivity
import com.example.administrator.smartschool.util.Utils
import com.example.administrator.smartschool.interfaces.UiOperation
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.util.AbTitleBarHelper

abstract class BaseAbAc : AbActivity(), UiOperation {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAbContentView(layoutResId)
        Utils.setAllOnClickListener(this, this)//Activity的根View
        AbTitleBarHelper.hideAbTitleBar(mAbTitleBar)()
        initOnCreate()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_back -> finish()
            else -> initOnClick(v)
        }
    }
}