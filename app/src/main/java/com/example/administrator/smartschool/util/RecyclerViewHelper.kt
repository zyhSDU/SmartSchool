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
    private fun initRecyclerView(recyclerView: RecyclerView, layoutManager: LayoutManager, adapter: Adapter<out ViewHolder>) {
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter
    }
    fun initGridRecyclerView(recyclerView: RecyclerView,context: Context,spanCount:Int,adapter: Adapter<out ViewHolder>) {
        initRecyclerView(
                recyclerView,
                GridLayoutManager(context, spanCount),
                adapter
        )
    }
    fun initVerticalRecyclerView(recyclerView: RecyclerView, context: Context, adapter:  Adapter<out ViewHolder>) {
        initRecyclerView(
                recyclerView,
                LinearLayoutManager(context),
                adapter
        )
    }
    fun initHorizontaRecyclerView(recyclerView: RecyclerView, context: Context, adapter:  Adapter<out ViewHolder>) {
        initRecyclerView(
                recyclerView,
                LinearLayoutManager(context,HORIZONTAL,false),
                adapter
        )
    }
}
