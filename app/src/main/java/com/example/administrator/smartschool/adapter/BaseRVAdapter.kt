package com.example.administrator.smartschool.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Administrator on 2018/5/31 0031.
 */
abstract class BaseRVAdapter(val context: Context, val datum: List<Any>) : RecyclerView.Adapter<BaseRVAdapter.MyViewHolder>(), LayoutAndViews {
    override fun getItemCount(): Int {
        return datum.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false))
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val views = Array<View>(viewsId.size, { i -> itemView.findViewById(viewsId[i]) })
    }
}