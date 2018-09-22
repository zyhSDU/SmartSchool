package com.example.administrator.smartschool.adapter.rv

import android.content.Context
import android.widget.Button
import android.widget.TextView
import com.ab.global.AbMenuItem
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.bean.BaseBean
import com.example.administrator.smartschool.bean.TogetherParter
import com.example.administrator.smartschool.net.CallUtil
import com.example.administrator.smartschool.temp.Temp
import kotlin.collections.ArrayList

/**
 * Created by Administrator on 2018/6/1 0001.
 */

class TogetherParterRVAdapter(context: Context, arrayList: List<Any>) : BaseRVAdapter(context, arrayList) {
    override val layoutId: Int
        get() = R.layout.item_together_parter

    override val viewsId: ArrayList<Int>
        get() = arrayListOf(
                R.id.tv_toId_item_together_parter,
                R.id.tv_id_item_together_parter,
                R.id.tv_userId_item_together_parter,
                R.id.tv_info_item_together_parter,
                R.id.btn_romveSomeone_item_together_parter
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val togetherParter = datum[position] as TogetherParter
        val tv_toId_item_together_parter = holder.views[0] as TextView
        val tv_id_item_together_parter = holder.views[1] as TextView
        val tv_userId_item_together_parter = holder.views[2] as TextView
        val tv_info_item_together_parter = holder.views[3] as TextView
        val btn_romveSomeone_item_together_parter = holder.views[4] as Button
        tv_toId_item_together_parter.text = togetherParter.toId.toString()
        tv_id_item_together_parter.text = togetherParter.id.toString()
        tv_userId_item_together_parter.text = togetherParter.userId.toString()
        tv_info_item_together_parter.text = togetherParter.info
        btn_romveSomeone_item_together_parter.setOnClickListener {
            CallUtil {
                val baseBean = it.obj as BaseBean
                showToast(baseBean.message)
            }.together_romveSomeone(togetherParter.toId, togetherParter.userId)
        }
    }
}
