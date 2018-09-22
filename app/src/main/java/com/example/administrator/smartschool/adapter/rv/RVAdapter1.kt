package com.example.administrator.smartschool.adapter.rv

import android.content.Context
import android.widget.TextView
import com.ab.global.AbMenuItem
import com.example.administrator.smartschool.R
import kotlin.collections.ArrayList

/**
 * Created by Administrator on 2018/6/1 0001.
 */

class RVAdapter1(context: Context, arrayList: List<Any>) : BaseRVAdapter(context, arrayList) {
    override val layoutId: Int
        get() = R.layout.list_pop_item

    override val viewsId: ArrayList<Int>
        get() = arrayListOf(R.id.pop_item)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val abMenuItem = datum[position] as AbMenuItem
        val textView = holder.views[0] as TextView
        textView.text = abMenuItem.text
    }
}
