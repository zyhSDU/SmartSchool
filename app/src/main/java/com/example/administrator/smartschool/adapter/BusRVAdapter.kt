package com.example.administrator.smartschool.adapter

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.bean.Bus
import com.example.administrator.smartschool.bean.IconBean
import java.util.ArrayList

/**
 * Created by Administrator on 2018/6/1 0001.
 */

class BusRVAdapter(context: Context, arrayList: List<Any>) : BaseRVAdapter(context, arrayList) {
    override val layoutId: Int
        get() = R.layout.item_bus

    override val viewsId: ArrayList<Int>
        get() = arrayListOf(R.id.tv_time_item_bus,R.id.tv_date_item_bus,R.id.tv_from_item_bus,R.id.tv_to_item_bus)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val bus = datum[position] as Bus
        val timeTV = holder.views[0] as TextView
        val dateTV = holder.views[1] as TextView
        val fromTV = holder.views[2] as TextView
        val toTV = holder.views[3] as TextView

        timeTV.text = bus.time
        dateTV.text = bus.date
        fromTV.text = bus.from
        toTV.text = bus.to
    }
}