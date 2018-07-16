package com.example.administrator.smartschool.fragment

/**
 * Created by Administrator on 2017/11/14 0014.
 */

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment : Fragment() {//！！切记！！导包为

    protected abstract val layoutId: Int
    //import android.app.Fragment;

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(layoutId, container, false)
        initView(view)
        return view
    }

    protected abstract fun initView(view: View): View?

    fun getmTag(): String {
        return javaClass.simpleName
    }

    fun startNewActivity(c: Class<*>) {
        startActivity(Intent(context, c))
    }
}
