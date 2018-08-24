package com.example.administrator.smartschool.adapter

import android.content.Context
import android.content.Intent
import android.widget.TextView
import com.example.administrator.smartschool.R
import java.util.ArrayList

/**
 * Created by Administrator on 2018/6/9 0009.
 */

class RVAdapter3(context: Context, list: List<Any>) : BaseRVAdapter(context, list) {
    override val layoutId: Int
        get() = R.layout.item_rv_3

    override val viewsId: ArrayList<Int>
        get() = arrayListOf(R.id.tv_item_rv_3)

    override fun onBindViewHolder(holder: BaseRVAdapter.MyViewHolder, position: Int) {
        val clazz = datum[position] as Class<*>
        val view = holder.views[0] as TextView
        view.text = clazz.simpleName
        view.setOnClickListener {
            context.startActivity(Intent(context, clazz))
        }
    }
}
