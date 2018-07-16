package com.example.administrator.smartschool.adapter

import android.content.Context
import android.widget.Button
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.bean.Bean0

import kotlin.collections.ArrayList

/**
 * Created by Administrator on 2018/5/31 0031.
 */

class RVAdapter0(context: Context, arrayList: List<Any>) : BaseRVAdapter(context, arrayList) {
    override val layoutId: Int
        get() = R.layout.item0

    override val arrOfResId: ArrayList<Int>
        get() = arrayListOf(R.id.btn_item_view)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val bean0 = arrOfData[position] as Bean0
        val button = holder.arrOfView[0] as Button
        button.text = bean0.str

        button.setOnClickListener {
            bean0.init()
        }
    }
}
