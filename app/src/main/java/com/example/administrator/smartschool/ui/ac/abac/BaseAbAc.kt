package com.example.administrator.smartschool.ui.ac.abac

/**
 * Created by Administrator on 2018/3/23 0023.
 */
import android.os.Bundle
import com.ab.activity.AbActivity
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.ui.UIInterface
import com.example.administrator.smartschool.util.AbTitleBarHelper
import com.example.administrator.smartschool.util.Utils

abstract class BaseAbAc : AbActivity(), AbAcInterface, UIInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAbContentView(layoutResId)
        Utils.setAllOnClickListener(this, this)//Activity的根View
        mAbTitleBar.titleTextButton.setTextColor(resources.getColor(R.color.black))
        try {
            titleBarTitle = intent.getStringExtra("titleBarTitle")?:titleBarTitle
        }catch (e:Exception){
        }
        AbTitleBarHelper.initAbTitleBar3(mAbTitleBar, this, titleBarTitle)
        initOnCreate()
    }

    protected fun startActivity(clazz: Class<*>) {
        startActivity(this, clazz)
    }

    protected var titleBarTitle = this.javaClass.simpleName
}