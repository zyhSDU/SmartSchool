package com.example.administrator.smartschool.util

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.ab.activity.AbActivity
import com.ab.global.AbMenuItem
import com.ab.view.titlebar.AbBottomBar
import com.ab.view.titlebar.AbTitleBar
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.adapter.rv.RVAdapter1
import com.example.administrator.smartschool.adapter.rv.RVAdapter2

/**
 * Created by Administrator on 2018/5/31 0031.
 */

object AbTitleBarHelper {
    fun initAbTitleBar(abTitleBar: AbTitleBar,
                       titleText: String,
                       titleBarBackgroundId: Int,
                       logoId: Int,
                       left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) {
        abTitleBar.clearRightView()
        abTitleBar.setTitleText(titleText)
        abTitleBar.setTitleBarBackground(titleBarBackgroundId)
        abTitleBar.setTitleTextMargin(left, top, right, bottom)

        abTitleBar.setLogo(logoId)
        showAbTitleBar(abTitleBar)()

    }

    fun initAbTitleBar2(abTitleBar: AbTitleBar,
                        titleText: String,
                        titleBarBackgroundId: Int,
                        left: Int = 32, top: Int = 0, right: Int = 0, bottom: Int = 0) {
        abTitleBar.clearRightView()
        abTitleBar.setTitleText(titleText)
        abTitleBar.setTitleBarBackground(titleBarBackgroundId)
        abTitleBar.setTitleTextMargin(left, top, right, bottom)
        showAbTitleBar(abTitleBar)()
    }


    private fun initAbTitleBar0(abTitleBar: AbTitleBar, abActivity: AbActivity) {
        initAbTitleBar(
                abTitleBar,
                abActivity.javaClass.simpleName,
                R.drawable.top_bg,
                R.drawable.button_selector_back,
                left = 10)
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
                left = 10)

        abTitleBar.setLogo2(R.drawable.button_selector_app)
        abTitleBar.addRightView(abActivity.mInflater.inflate(R.layout.ok_btn, null))
        abTitleBar.setLogoOnClickListener {
            initAbTitleBar0(abTitleBar, abActivity)
            abTitleBar.logoView2.visibility = View.GONE
        }
    }

    private fun initAbTitleBar2(abTitleBar: AbTitleBar, abActivity: AbActivity) {
        AbTitleBarHelper.initAbTitleBar(
                abTitleBar,
                abActivity.resources.getString(R.string.app_name),
                R.color.black,
                R.drawable.ic_arrow_back_white_24dp)
        val layoutParams = abTitleBar.logoView.layoutParams as LinearLayout.LayoutParams
        layoutParams.setMargins(16, 0, 16, 0)
        abTitleBar.setLogoOnClickListener {
            abActivity.finish()
        }
    }

    fun initAbTitleBar3(abTitleBar: AbTitleBar, abActivity: AbActivity, string: String) {
        initAbTitleBar2(abTitleBar, string, R.color.white)
        val layoutParams = abTitleBar.logoView.layoutParams as LinearLayout.LayoutParams
        layoutParams.setMargins(16, 0, 16, 0)
        abTitleBar.setLogoOnClickListener {
            abActivity.finish()
        }
    }
    fun initAbTitleBar4(abTitleBar: AbTitleBar, abActivity: AbActivity, string: String) {
        initAbTitleBar2(abTitleBar, string, R.color.base)
        val layoutParams = abTitleBar.logoView.layoutParams as LinearLayout.LayoutParams
        layoutParams.setMargins(32, 0, 16, 0)
        abTitleBar.setLogoOnClickListener {
            abActivity.finish()
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

    fun addRightView(abTitleBar: AbTitleBar, vararg views: View) {
        views.map { abTitleBar.addRightView(it) }
    }

    @SuppressLint("InflateParams")
    val addRightView0 = { abTitleBar: AbTitleBar, abActivity: AbActivity ->
        {
            val views = ArrayList<View>()
            val view0 = abActivity.mInflater.inflate(R.layout.view_weather, null)

            views.add(view0)
            val pixels = DimensHelper.getPixelsFromDP(abActivity, 8)
            views.map {
                abTitleBar.addRightView(it)
                ViewHelper.setMargins(it, left = pixels, right = pixels)
            }
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
        RecyclerViewHelper.initVerticalRecyclerView(recyclerView, abActivity, rvAdapter)
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
        RecyclerViewHelper.initVerticalRecyclerView(recyclerView, abActivity, RVAdapter2(abActivity, listOfAbMenuItem))
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
