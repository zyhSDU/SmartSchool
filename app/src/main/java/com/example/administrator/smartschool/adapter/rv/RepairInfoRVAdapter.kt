package com.example.administrator.smartschool.adapter.rv

import android.content.Context
import android.widget.TextView
import com.ab.global.AbMenuItem
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.bean.RepairInfo
import kotlin.collections.ArrayList

/**
 * Created by Administrator on 2018/6/1 0001.
 */

class RepairInfoRVAdapter(context: Context, arrayList: List<Any>) : BaseRVAdapter(context, arrayList) {
    override val layoutId: Int
        get() = R.layout.item_repair_info

    override val viewsId: ArrayList<Int>
        get() = arrayListOf(R.id.tv_describe_item_repair_info, R.id.tv_status_item_repair_info, R.id.tv_reportTime_item_repair_info)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val tv_describe = holder.views[0] as TextView
        val tv_status = holder.views[1] as TextView
        val tv_reportTime = holder.views[2] as TextView
        val repairInfo = datum[position] as RepairInfo
        tv_describe.text = repairInfo.zdescribe
        tv_status.text = repairInfo.status.toString()
        tv_reportTime.text = repairInfo.reportTime
    }
}
