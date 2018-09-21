package com.example.administrator.smartschool.ui.ac.abac.tab

import android.view.View
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.adapter.rv.RepairInfoRVAdapter
import com.example.administrator.smartschool.bean.BaseBeanWithObject
import com.example.administrator.smartschool.bean.RepairInfoListBean
import com.example.administrator.smartschool.net.CallUtil
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
        getPrivateRepairs(0)
    }

    override fun initOnClick(view: View, id: Int) {
        when (id) {
            R.id.btn_report -> {
                val string = et_report.text.toString()
                submitRepair(string)
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

    private fun submitRepair(describe: String) {
        if (describe.length > 80) {
            showToast("不得超过80个字")
            return
        }
        CallUtil({
            val baseBean = it.obj as BaseBeanWithObject
            when (baseBean.code) {
                0 -> {
                    showToast("数据库中的报修id${baseBean.`object`.toString().toDouble().toInt()}")
                }
            }
        }).submitRepair(describe)
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
