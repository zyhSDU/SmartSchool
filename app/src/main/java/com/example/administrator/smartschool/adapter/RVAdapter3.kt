package com.example.administrator.smartschool.adapter

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.bean.IconBean
import java.util.ArrayList

/**
 * Created by Administrator on 2018/6/1 0001.
 */

class RVAdapter3(context: Context, arrayList: List<Any>) : BaseRVAdapter(context, arrayList) {
    override val layoutId: Int
        get() = R.layout.item_rv1_fg1_main

    override val viewsId: ArrayList<Int>
        get() = arrayListOf(R.id.iv_item_rv1_fg1_main,R.id.tv_item_rv1_fg1_main)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val iconBean = datum[position] as IconBean
        val imageView = holder.views[0] as ImageView
        val textView = holder.views[1] as TextView
        imageView.setImageDrawable(context.resources.getDrawable(iconBean.imageViewId))
        textView.text = iconBean.string
        imageView.setOnClickListener {
            iconBean.onClick()
        }
    }
}