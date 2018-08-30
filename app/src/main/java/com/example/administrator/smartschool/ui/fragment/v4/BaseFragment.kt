package com.example.administrator.smartschool.ui.fragment.v4

/**
 * Created by Administrator on 2017/11/14 0014.
 */
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.administrator.smartschool.ui.UIInterface
import com.example.administrator.smartschool.ui.fragment.FgInterface

abstract class BaseFragment : Fragment(), UIInterface,FgInterface {
    protected abstract val layoutId: Int
    protected lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(layoutId, container, false)
        initView(rootView)
        return rootView
    }

    protected open fun initView(view: View) {
    }

    protected fun startActivity(clazz: Class<*>) {
        startActivity(activity, clazz)
    }

    protected fun showToast(text: String?) {
        showToast(activity,text)
    }
}
