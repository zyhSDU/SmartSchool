package com.example.administrator.smartschool.adapter.rv

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent.getIntent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.ab.global.AbMenuItem
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.bean.BaseBean
import com.example.administrator.smartschool.bean.Lost
import com.example.administrator.smartschool.bean.LostBean
import com.example.administrator.smartschool.net.CallUtil
import com.example.administrator.smartschool.temp.Temp
import com.example.administrator.smartschool.util.Base64Util
import kotlin.collections.ArrayList

/**
 * Created by Administrator on 2018/6/1 0001.
 */

class LostRVAdapter(context: Context, arrayList: List<Any>) : BaseRVAdapter(context, arrayList) {
    override val layoutId: Int
        get() = R.layout.item_lost

    override val viewsId: ArrayList<Int>
        get() = arrayListOf(
                R.id.tv_zdescribe_item_lost,
                R.id.tv_time_item_lost,
                R.id.tv_status_item_lost,
                R.id.ll_tvs_item_lost,
                R.id.iv_item_lost,
                R.id.btn_finishLost_item_lost
        )

    var temp_iv: ImageView? = null
    var ints = Array(datum.size, { 0 })
    //0没点过，1点过了但null，2点过了切非空

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val lost = datum[position] as Lost
        val tv_zdescribe_item_lost = holder.views[0] as TextView
        val tv_time_item_lost = holder.views[1] as TextView
        val tv_status_item_lost = holder.views[2] as TextView
        val ll_tvs_item_lost = holder.views[3] as LinearLayout
        val iv_item_lost = holder.views[4] as ImageView
        val btn_finishLost_item_lost = holder.views[5] as Button

        tv_zdescribe_item_lost.text = lost.zdescribe
        tv_time_item_lost.text = lost.time
        when {
            lost.status==0 -> {
                tv_status_item_lost.text = "未认领"
                tv_status_item_lost.setTextColor(context.resources.getColor(R.color.red_label))
            }
            lost.status==1 -> {
                tv_status_item_lost.text = "已认领"
                tv_status_item_lost.setTextColor(context.resources.getColor(R.color.green))
            }
        }

        ll_tvs_item_lost.setOnClickListener {
            val i = ints[position]
            when (i) {
                0 -> {
                    lost_getLostInfo(lost.id, iv_item_lost, position)
                    iv_item_lost.visibility = View.VISIBLE
                    if (temp_iv != null) {
                        temp_iv!!.visibility = View.GONE
                    }
                    temp_iv = iv_item_lost
                }
                1 -> {
                }
                else -> {
                    iv_item_lost.visibility = View.VISIBLE
                    if (temp_iv != null) {
                        temp_iv!!.visibility = View.GONE
                    }
                    temp_iv = iv_item_lost
                }
            }
        }
        btn_finishLost_item_lost.setOnClickListener {
            lost_finishLost(lost.id)
        }
    }

    private fun lost_finishLost(id:Int){
        CallUtil{
            val baseBean = it.obj as BaseBean
            Temp.showToast(context,baseBean.message)
        }.lost_finishLost(id)
    }
    private fun lost_getLostInfo(id: Int, imageView: ImageView, position: Int) {
        CallUtil {
            val lostBean = it.obj as LostBean
            when (lostBean.code) {
                0 -> {
                    val lost = lostBean.`object`
                    if (lost == null) {
                        ints[position] = 1
                    } else {
                        ints[position] = 2
                        if (lost.pic != null) {
                            imageView.scaleType = ImageView.ScaleType.FIT_XY
                            imageView.setImageBitmap(Base64Util.base64ToBitmap(lost.pic!!))
                        }
                    }
                }
                else -> {
                    ints[position] = 1
                }
            }
        }.lost_getLostInfo(id)
    }
}
