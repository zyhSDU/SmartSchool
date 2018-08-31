package com.example.administrator.smartschool.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ab.global.AbMenuItem
import com.example.administrator.smartschool.R

/**
 * Created by Administrator on 2018/6/9 0009.
 */

class LVAdapter0(
        context: Context,
        list: List<AbMenuItem>
) : BaseLVAdapter(context, list) {

    override val layoutId: Int
        get() = R.layout.list_pop_item

    override val viewsId: ArrayList<Int>
        get() = arrayListOf(R.id.pop_item)

    override fun setViews(holder: ViewHolder, position: Int) {
        val item = list[position]
        val textView = holder.views[0] as TextView
        textView.text = item.text
    }
}