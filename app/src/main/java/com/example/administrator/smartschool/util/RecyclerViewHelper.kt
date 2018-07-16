package com.example.administrator.smartschool.util

import android.content.Context
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.*

/**
 * Created by Administrator on 2018/5/31 0031.
 */

object RecyclerViewHelper {
    fun initRecyclerView(recyclerView: RecyclerView,layoutManager: LayoutManager,adapter: Adapter<ViewHolder>) {
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter
    }
    fun initGridRecyclerView(recyclerView: RecyclerView,context: Context,spanCount:Int,adapter: Adapter<ViewHolder>) {
        recyclerView.layoutManager = GridLayoutManager(context, spanCount)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter
    }
    fun initVerticalLinearRecyclerView(recyclerView: RecyclerView,context: Context,adapter:  Adapter<out ViewHolder>) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter
    }
    fun initHorizontalLinearRecyclerView(recyclerView: RecyclerView,context: Context,adapter:  Adapter<out ViewHolder>) {
        recyclerView.layoutManager = LinearLayoutManager(context,HORIZONTAL,false)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter
    }
}
