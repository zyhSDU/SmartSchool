package com.example.administrator.smartschool.util

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import com.ab.activity.AbActivity
import com.ab.global.AbMenuItem
import com.ab.view.titlebar.AbBottomBar
import com.ab.view.titlebar.AbTitleBar
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.adapter.RVAdapter1
import com.example.administrator.smartschool.adapter.RVAdapter2

/**
 * Created by Administrator on 2018/5/31 0031.
 */

object AbTitleBarHelper {
    fun initAbTitleBar(abTitleBar: AbTitleBar,
                       titleText: String,
                       titleBarBackgroundId: Int,
                       logoId: Int, left: Int, top: Int, right: Int, bottom: Int) {
        abTitleBar.clearRightView()
        abTitleBar.setTitleText(titleText)
        abTitleBar.setTitleBarBackground(titleBarBackgroundId)
        abTitleBar.setTitleTextMargin(left, top, right, bottom)

        abTitleBar.setLogo(logoId)
        showAbTitleBar(abTitleBar)()

    }

    fun initAbTitleBar0(abTitleBar: AbTitleBar, abActivity: AbActivity) {
        initAbTitleBar(
                abTitleBar,
                abActivity.javaClass.simpleName,
                R.drawable.top_bg,
                R.drawable.button_selector_back,
                10, 0, 0, 0)
        abTitleBar.setLogoOnClickListener {
            abActivity.finish()
        }
    }

    @SuppressLint("InflateParams")
    private fun initAbTitleBar1(abTitleBar: AbTitleBar, abActivity: AbActivity) {
        initAbTitleBar(
                abTitleBar,
                "正在修改",
                R.drawable.top_bg2,
                R.drawable.button_selector_delete,
                10, 0, 0, 0)

        abTitleBar.setLogo2(R.drawable.button_selector_app)
        abTitleBar.addRightView(abActivity.mInflater.inflate(R.layout.ok_btn, null))
        abTitleBar.setLogoOnClickListener {
            initAbTitleBar0(abTitleBar, abActivity)
            abTitleBar.logoView2.visibility = View.GONE
        }
    }



    val hideAbTitleBar = { abTitleBar: AbTitleBar ->
        {
            abTitleBar.visibility = View.GONE
        }
    }

    val showAbTitleBar = { abTitleBar: AbTitleBar ->
        {
            abTitleBar.visibility = View.VISIBLE
        }
    }

    val hideRightPartOfAbTitleBar = { abTitleBar: AbTitleBar ->
        {
            abTitleBar.clearRightView()
        }
    }

    fun showRightPartOfAbTitleBar(abTitleBar: AbTitleBar, vararg views: View) {
        abTitleBar.clearRightView()
        views.map { abTitleBar.addRightView(it) }
    }

    @SuppressLint("InflateParams")
    val showRightPartOfAbTitleBar0 = { abTitleBar: AbTitleBar, abActivity: AbActivity ->
        {
            abTitleBar.clearRightView()
            val views = ArrayList<View>()
            val view0 = abActivity.mInflater.inflate(R.layout.more_but, null)
            val view1 = abActivity.mInflater.inflate(R.layout.app_btn, null)
            val button0 = view0.findViewById<Button>(R.id.moreBtn)
            val button1 = view1.findViewById<Button>(R.id.appBtn)
            button0.setOnClickListener { abActivity.showToast("点击") }
            button1.setOnClickListener { abActivity.showToast("继续点击") }
            views.add(view0)
            views.add(view1)
            views.map { abTitleBar.addRightView(it) }
            val a = 1//占位保平安
        }
    }

    val changeAbTitleBar = { abTitleBar: AbTitleBar, abActivity: AbActivity ->
        {
            initAbTitleBar1(abTitleBar, abActivity)
        }
    }

    val transparentBackgroundOfAbTitleBar = { abTitleBar: AbTitleBar ->
        {
            abTitleBar.setTitleBarBackgroundColor(Color.TRANSPARENT)
        }
    }

    val hideDropDownOfAbTitleBar = { abTitleBar: AbTitleBar ->
        {
            abTitleBar.setTitleTextBackgroundDrawable(null)
            abTitleBar.setTitleTextOnClickListener(null)
        }
    }

    @SuppressLint("InflateParams")
    fun showDropDownOfAbTitleBar(abTitleBar: AbTitleBar, abActivity: AbActivity, strings: Array<String>) {
        val listOfAbMenuItem = strings.indices.map { AbMenuItem(strings[it]) }
        abTitleBar.setTitleTextBackgroundResource(R.drawable.drop_down_title_btn)
        val view0 = abActivity.mInflater.inflate(R.layout.list_pop, null)
        val recyclerView = view0.findViewById<RecyclerView>(R.id.pop_list)
        val rvAdapter = RVAdapter1(abActivity, listOfAbMenuItem)
        RecyclerViewHelper.initVerticalLinearRecyclerView(recyclerView, abActivity, rvAdapter)
        abTitleBar.setTitleTextDropDown(view0)
    }

    val showDropDownOfAbTitleBar0 = { abTitleBar: AbTitleBar, abActivity: AbActivity ->
        {
            showDropDownOfAbTitleBar(abTitleBar, abActivity, arrayOf("aa", "bb", "cc", "dd"))
        }
    }

    val hideAbBottomBar = { abBottomBar: AbBottomBar ->
        {
            abBottomBar.visibility = View.GONE
        }
    }

    @SuppressLint("InflateParams")
    fun showAbBottomBar(abBottomBar: AbBottomBar, abActivity: AbActivity, strings: Array<String>) {
        val listOfAbMenuItem = strings.indices.map { AbMenuItem(strings[it]) }
        abBottomBar.visibility = View.VISIBLE
        val arrayListOfBtn = ArrayList<Button>()
        val view0 = abActivity.mInflater.inflate(R.layout.list_pop, null)
        val view1 = abActivity.mInflater.inflate(R.layout.bottom_bar, null)
        arrayListOfBtn.add(view1.findViewById(R.id.tab_1))
        arrayListOfBtn.add(view1.findViewById(R.id.tab_2))
        arrayListOfBtn.add(view1.findViewById(R.id.tab_3))
        arrayListOfBtn.add(view1.findViewById(R.id.tab_4))
        arrayListOfBtn.add(view1.findViewById(R.id.tab_5))

        abBottomBar.setBottomView(view1)
        val recyclerView = view0.findViewById<RecyclerView>(R.id.pop_list)
        RecyclerViewHelper.initVerticalLinearRecyclerView(recyclerView, abActivity, RVAdapter2(abActivity, listOfAbMenuItem))
        for (i in arrayListOfBtn.indices) {
            abBottomBar.setDropDown(arrayListOfBtn[i], view0)
        }
    }

//    fun showAbBottomBar0(abBottomBar: AbBottomBar, abActivity: AbActivity) {
//        showAbBottomBar(abBottomBar, abActivity, arrayOf("分享", "收藏", "好评", "搜索"))
//    }

    val showAbBottomBar0 = { abBottomBar: AbBottomBar, abActivity: AbActivity ->
        {
            showAbBottomBar(abBottomBar, abActivity, arrayOf("分享", "收藏", "好评", "搜索"))
        }
    }
}
