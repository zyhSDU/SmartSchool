package com.example.administrator.smartschool.adapter.rv

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.bean.BaseBean
import com.example.administrator.smartschool.bean.Together
import com.example.administrator.smartschool.bean.TogetherParterListBean
import com.example.administrator.smartschool.net.CallUtil
import com.example.administrator.smartschool.temp.Temp
import com.example.administrator.smartschool.ui.ac.abac.tab.TogetherParterListActivity
import com.example.administrator.smartschool.util.DialogHelper

/**
 * Created by Administrator on 2018/6/1 0001.
 */

class TogetherInfoRVAdapter(
        context: Context,
        arrayList: List<Any>
) : BaseRVAdapter(context, arrayList) {
    override val layoutId: Int
        get() = R.layout.item_together

    override val viewsId: ArrayList<Int>
        get() = arrayListOf(
                R.id.tv_id_item_together,
                R.id.tv_schoolId_item_together,
                R.id.tv_time_item_together,

                R.id.tv_num_item_together,
                R.id.tv_status_item_together,
                R.id.tv_subId_item_together,

                R.id.tv_type_item_together,
                R.id.tv_zdescribe_item_together,
                R.id.tv_selfStatus_item_together,
                R.id.btn_join_item_together,
                R.id.btn_see_joiner_item_together,
                R.id.btn_quitTog_item_together
        )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val tv_id_item_together = holder.views[0] as TextView
        val tv_schoolId_item_together = holder.views[1] as TextView
        val tv_time_item_together = holder.views[2] as TextView
        val tv_num_item_together = holder.views[3] as TextView
        val tv_status_item_together = holder.views[4] as TextView
        val tv_subId_item_together = holder.views[5] as TextView
        val tv_type_item_together = holder.views[6] as TextView
        val tv_zdescribe_item_together = holder.views[7] as TextView
        val tv_selfStatus_item_together = holder.views[8] as TextView
        val btn_join_item_together = holder.views[9] as Button
        val btn_see_joiner_item_together = holder.views[10] as Button
        val btn_quitTog_item_together=holder.views[11] as Button


        val together = datum[position] as Together
        tv_id_item_together.text = together.id.toString()
        tv_schoolId_item_together.text = together.schoolId.toString()
        tv_time_item_together.text = together.time

        tv_num_item_together.text = together.num.toString()
        tv_status_item_together.text = together.status.toString()
        tv_subId_item_together.text = together.subId.toString()

        tv_type_item_together.text = together.type.toString()
        tv_zdescribe_item_together.text = together.zdescribe
        tv_selfStatus_item_together.text = together.selfStatus.toString()
        btn_join_item_together.setOnClickListener {
            DialogHelper.build(
                    "我想加入",
                    context,
                    R.layout.dialog_join_together,
                    init = { dialogView ->
                        val editText = dialogView.findViewById<EditText>(R.id.et_dialog_join_together)
                        return@build { dialog, which ->
                            together_partTog(together.id, editText.text.toString())
                        }
                    }
            ).show()
        }
        btn_see_joiner_item_together.setOnClickListener {
            together_getParts(together.id)
        }
        btn_quitTog_item_together.setOnClickListener {
            together_quitTog(together.id)
        }
    }
    private fun together_quitTog(toId: Int) {
        CallUtil {
            val baseBean = it.obj as BaseBean
            showToast( baseBean.message)
        }.together_quitTog(toId)
    }

    private fun together_partTog(toId: Int, info: String) {
        CallUtil {
            val baseBean = it.obj as BaseBean
            showToast( baseBean.message)
        }.together_partTog(toId, info)
    }

    private fun together_getParts(toId: Int) {
        CallUtil {
            val baseBean = it.obj as TogetherParterListBean
            Temp.showToast(context, baseBean.message)
            if (baseBean.code==0){
                val list = baseBean.`object`!!
                TogetherParterListActivity.start(context,list)
            }
        }.together_getParts(toId)
    }
}
