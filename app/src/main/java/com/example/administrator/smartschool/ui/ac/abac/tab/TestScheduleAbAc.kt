package com.example.administrator.smartschool.ui.ac.abac.tab

import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.adapter.rv.AnswerReportRVAdapter
import com.example.administrator.smartschool.ui.ac.abac.BaseAbAc
import com.example.administrator.smartschool.util.RecyclerViewHelper
import kotlinx.android.synthetic.main.ac_test_schedule.*

/**
 * Created by Administrator on 2018/9/2 0002.
 */

class TestScheduleAbAc: BaseAbAc() {
    override val layoutResId: Int
        get() = R.layout.ac_test_schedule

    override fun initOnCreate() {
        RecyclerViewHelper.initVerticalRecyclerView(
                rv_test_schedule, this,
                AnswerReportRVAdapter(this, arrayListOf("1"))
        )
    }
}
