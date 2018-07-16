package com.example.administrator.smartschool.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlin.collections.ArrayList

/**
 * Created by Administrator on 2018/5/31 0031.
 */
abstract class BaseRVAdapter(
        protected val context: Context,
        protected val arrOfData: List<Any>
) : RecyclerView.Adapter<BaseRVAdapter.MyViewHolder>() {
    protected abstract val layoutId: Int
    protected abstract val arrOfResId:ArrayList<Int>

    override fun getItemCount(): Int {
        return arrOfData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false))
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val arrOfView = arrayOfNulls<View>(arrOfResId.size)

        init {
            arrOfResId.indices.forEach { i -> arrOfView[i] = itemView.findViewById(arrOfResId[i]) }
        }
    }
}