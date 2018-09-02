package com.example.administrator.smartschool.ui.ac.abac.tab

import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.adapter.rv.AnswerReportRVAdapter
import com.example.administrator.smartschool.ui.ac.abac.BaseAbAc
import com.example.administrator.smartschool.util.RecyclerViewHelper
import kotlinx.android.synthetic.main.ac_answer_report.*

/**
 * Created by Administrator on 2018/7/18 0018.
 */

class AnswerReportAbAc : BaseAbAc() {
    override val layoutResId: Int
        get() = R.layout.ac_answer_report

    override fun initOnCreate() {
        RecyclerViewHelper.initVerticalRecyclerView(
                rv_answer_report, this,
                AnswerReportRVAdapter(this, arrayListOf("1"))
        )
    }
}
