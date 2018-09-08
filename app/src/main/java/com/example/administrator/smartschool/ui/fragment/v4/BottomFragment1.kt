package com.example.administrator.smartschool.ui.fragment.v4

import android.support.v7.widget.RecyclerView
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.adapter.rv.RVAdapter3
import com.example.administrator.smartschool.bean.IconBean
import com.example.administrator.smartschool.ui.ac.abac.tab.*
import com.example.administrator.smartschool.util.RecyclerViewHelper

/**
 * Created by zhangYuHan on 2017/8/2.
 */

class BottomFragment1 : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fr_bottom_main_1

    override fun initView() {
        val itemList: ArrayList<IconBean> = ArrayList()

        itemList.add(getIconBean("天气查询", WeatherAbAc::class.java))
        itemList.add(getIconBean("校车查询", BusAbAc::class.java))
        itemList.add(getIconBean("提交报修", ReportAbAc::class.java))
        itemList.add(getIconBean("回复报修", AnswerReportAbAc::class.java))
        itemList.add(getIconBean("成绩查询", ScoreAbAc::class.java))
        itemList.add(getIconBean("考试安排", TestScheduleAbAc::class.java))

        itemList.add(getIconBean("提交报修", ReportAbAc::class.java))
        itemList.add(getIconBean("受理报修", AnswerReportAbAc::class.java))

        val recyclerView1 = rootView.findViewById<RecyclerView>(R.id.rv1_fg1_main)
        RecyclerViewHelper.initGridRecyclerView(recyclerView1, activity, 3, RVAdapter3(activity, itemList))
    }

    private fun getIconBean(string: String, clazz: Class<*>): IconBean {
        return IconBean(string = string, activity = activity, clazz = clazz)
    }
}
