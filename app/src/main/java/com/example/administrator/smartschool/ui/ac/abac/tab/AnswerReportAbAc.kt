package com.example.administrator.smartschool.ui.ac.abac.tab

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.adapter.rv.RepairInfoRVAdapter
import com.example.administrator.smartschool.bean.RepairInfoBean
import com.example.administrator.smartschool.ui.ac.abac.BaseAbAc
import com.example.administrator.smartschool.util.CallUtil
import com.example.administrator.smartschool.util.RecyclerViewHelper
import kotlinx.android.synthetic.main.ac_answer_report.*

/**
 * Created by Administrator on 2018/7/18 0018.
 */

class AnswerReportAbAc : BaseAbAc() {
    override val layoutResId: Int
        get() = R.layout.ac_answer_report

    override fun initOnCreate() {
        getPrivateRepairs(0)
    }

    private fun getPrivateRepairs(page: Int) {
        CallUtil({
            val repairInfoBean = it.obj as RepairInfoBean

            showToast("${repairInfoBean.code}==${repairInfoBean.message}")

            when (repairInfoBean.code) {
                0 -> {
                    RecyclerViewHelper.initVerticalRecyclerView(
                            rv_answer_report, this@AnswerReportAbAc,
                            RepairInfoRVAdapter(this@AnswerReportAbAc, repairInfoBean.`object`!!)
                    )
                }
            }
        }).getRepairs(page)
    }
}
