package com.example.administrator.smartschool.adapter.rv

import android.content.Context
import android.widget.TextView
import com.ab.global.AbMenuItem
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.bean.Signer
import kotlin.collections.ArrayList

/**
 * Created by Administrator on 2018/6/1 0001.
 */

class SignerListRVAdapter(context: Context, arrayList: List<Any>) : BaseRVAdapter(context, arrayList) {
    override val layoutId: Int
        get() = R.layout.item_signer

    override val viewsId: ArrayList<Int>
        get() = arrayListOf(
                R.id.tv_username_item_signer,
                R.id.tv_time_item_signer
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val signer = datum[position] as Signer
        val tv_username_item_signer = holder.views[0] as TextView
        val tv_time_item_signer = holder.views[1] as TextView
        tv_username_item_signer.text = signer.username
        tv_time_item_signer.text = signer.time
    }
}
