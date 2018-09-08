package com.example.administrator.smartschool.ui.ac.abac.tab

import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.ui.ac.abac.BaseAbAc
import com.example.administrator.smartschool.util.RecyclerViewHelper
import kotlinx.android.synthetic.main.ac_score.*

/**
 * Created by Administrator on 2018/7/18 0018.
 */

class ScoreAbAc : BaseAbAc() {
    override val layoutResId: Int
        get() = R.layout.ac_score

    override fun initOnCreate() {
//        RecyclerViewHelper.initVerticalRecyclerView(
//                rv_score, this,
//                AnswerReportRVAdapter(this, arrayListOf("1"))
//        )
    }
}
