package com.example.administrator.smartschool.ui.ac.abac.tab

import android.view.View
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.adapter.rv.AnswerReportRVAdapter
import com.example.administrator.smartschool.ui.ac.abac.BaseAbAc
import com.example.administrator.smartschool.util.RecyclerViewHelper
import com.example.administrator.smartschool.util.ViewHelper
import kotlinx.android.synthetic.main.ac_report.*

/**
 * Created by Administrator on 2018/7/18 0018.
 */

class ReportAbAc : BaseAbAc() {
    override val layoutResId: Int
        get() = R.layout.ac_report

    override fun initOnCreate() {
        RecyclerViewHelper.initVerticalRecyclerView(
                rv_report, this,
                AnswerReportRVAdapter(this, arrayListOf("1"))
        )
    }

    override fun initOnClick(view: View, id: Int) {
        when (id) {
            R.id.btn_report -> {
                val string = et_report.text.toString()
            }
            R.id.btn_back2report -> {
                ViewHelper.run {
                    setViewVisibility(false, btn_back2report)
                    setViewVisibility(true, et_report, btn_report, btn_show_report_list)
                }
            }
            R.id.btn_show_report_list -> {
                ViewHelper.run {
                    setViewVisibility(false, et_report, btn_report, btn_show_report_list)
                    setViewVisibility(true, btn_back2report)
                }
            }
        }
    }
}
