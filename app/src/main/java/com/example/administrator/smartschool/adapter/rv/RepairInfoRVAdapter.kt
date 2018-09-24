package com.example.administrator.smartschool.adapter.rv

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.bean.BaseBean
import com.example.administrator.smartschool.bean.RepairInfo
import com.example.administrator.smartschool.net.CallUtil
import com.example.administrator.smartschool.util.ViewHelper

/**
 * Created by Administrator on 2018/6/1 0001.
 */

class RepairInfoRVAdapter(
        context: Context,
        arrayList: List<Any>,
        val isAdministrator: Boolean
) : BaseRVAdapter(context, arrayList) {
    override val layoutId: Int
        get() = R.layout.item_repair_info

    override val viewsId: ArrayList<Int>
        get() = arrayListOf(
                R.id.tv_describe_item_repair_info,
                R.id.tv_status_item_repair_info,
                R.id.tv_reportTime_item_repair_info,
                R.id.btn_deal_repair_item_repair_info,
                R.id.btn_finish_repair_item_repair_info
        )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val tv_describe = holder.views[0] as TextView
        val tv_status = holder.views[1] as TextView
        val tv_reportTime = holder.views[2] as TextView
        val btn_deal_repair = holder.views[3] as Button
        val btn_finish_repair = holder.views[4] as Button

        val repairInfo = datum[position] as RepairInfo

        tv_describe.text = repairInfo.zdescribe
        when {
            repairInfo.status==0 -> {
                tv_status.text = "未受理"
                tv_status.setTextColor(context.resources.getColor(R.color.red_label))
            }
            repairInfo.status==1 -> {
                tv_status.text = "受理中"
                tv_status.setTextColor(context.resources.getColor(R.color.yellow))
            }
            repairInfo.status==2 -> {
                tv_status.text = "已解决"
                tv_status.setTextColor(context.resources.getColor(R.color.green))
            }
        }
        tv_reportTime.text = repairInfo.reportTime
        ViewHelper.run {
            setViewVisibility(isAdministrator, btn_deal_repair)
            setViewVisibility(!isAdministrator, btn_finish_repair)
        }
        btn_deal_repair.setOnClickListener {
            dealRepair(repairInfo.id)
        }
        btn_finish_repair.setOnClickListener {
            finishRepair(repairInfo.id)
        }
    }

    private fun dealRepair(id: Int) {
        CallUtil {
            val baseBean = it.obj as BaseBean
            showToast(baseBean.message)
        }.dealRepair(id)
    }

    private fun finishRepair(id: Int) {
        CallUtil {
            val baseBean = it.obj as BaseBean
            showToast(baseBean.message)
        }.finishRepair(id)
    }
}
