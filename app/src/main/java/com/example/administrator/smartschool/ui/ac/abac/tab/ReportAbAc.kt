package com.example.administrator.smartschool.ui.ac.abac.tab

import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.adapter.rv.RepairInfoRVAdapter
import com.example.administrator.smartschool.bean.RepairInfoListBean
import com.example.administrator.smartschool.net.CallUtil
import com.example.administrator.smartschool.ui.ac.abac.BaseAbAc
import com.example.administrator.smartschool.util.RecyclerViewHelper
import kotlinx.android.synthetic.main.ac_report.*

/**
 * Created by Administrator on 2018/7/18 0018.
 */

class ReportAbAc : BaseAbAc() {
    override val layoutResId: Int
        get() = R.layout.ac_report

    override fun initOnCreate() {
        btn_add_report.setOnClickListener {
            startActivity(AddReportActivity::class.java)
        }
        getPrivateRepairs(0)
    }

    private fun getPrivateRepairs(page: Int) {
        CallUtil({
            val repairInfoBean = it.obj as RepairInfoListBean
            when (repairInfoBean.code) {
                0 -> {
                    RecyclerViewHelper.initVerticalRecyclerView(
                            rv_report, this@ReportAbAc,
                            RepairInfoRVAdapter(
                                    this@ReportAbAc,
                                    repairInfoBean.`object`!!,
                                    false
                            )
                    )
                }
            }
        }).getPrivateRepairs(page)
    }
}
