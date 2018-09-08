package com.example.administrator.smartschool.ui.ac.abac.tab

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import android.view.View
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.adapter.rv.RepairInfoRVAdapter
import com.example.administrator.smartschool.bean.BaseBeanWithObject
import com.example.administrator.smartschool.bean.RepairInfoBean
import com.example.administrator.smartschool.ui.ac.abac.BaseAbAc
import com.example.administrator.smartschool.util.CallUtil
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
                getPrivateRepairs(0)
            }
        }
    }

    private fun submitRepair(describe: String) {
        startThread {
            CallUtil(submitRepairHandler).submitRepair(describe)
        }
    }

    private val submitRepairHandler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            val baseBean = msg.obj as BaseBeanWithObject

            showToast("${baseBean.code}==${baseBean.message}")

            when (baseBean.code) {
                0 -> {
                    showToast("数据库中的报修id${baseBean.`object`.toString().toDouble().toInt()}")
                }
            }
        }
    }

    private fun getPrivateRepairs(page: Int) {
        startThread {
            CallUtil(getPrivateRepairsHandler).getPrivateRepairs(page)
        }
    }

    private val getPrivateRepairsHandler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            val repairInfoBean = msg.obj as RepairInfoBean

            showToast("${repairInfoBean.code}==${repairInfoBean.message}")

            when (repairInfoBean.code) {
                0 -> {
                    RecyclerViewHelper.initVerticalRecyclerView(
                            rv_report, this@ReportAbAc,
                            RepairInfoRVAdapter(this@ReportAbAc, repairInfoBean.`object`!!)
                    )
                }
            }
        }
    }
}
