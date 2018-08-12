package com.example.administrator.smartschool.fragment_v4

/**
 * Created by Administrator on 2017/11/14 0014.
 */
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment : Fragment() {
    protected abstract val layoutId: Int
    protected lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(layoutId, container, false)
        initView(rootView)
        return rootView
    }

    protected open fun initView(view: View?) {
    }

}
