package com.example.administrator.smartschool.adapter.lv

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

import com.ab.global.AbMenuItem
import com.example.administrator.smartschool.adapter.LayoutAndViews

/**
 * Created by Administrator on 2018/6/9 0009.
 */

abstract class BaseLVAdapter(
        val context: Context,
        val list: List<AbMenuItem>
) : BaseAdapter(), LayoutAndViews {

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutId, null)
            holder = ViewHolder(convertView)
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }
        setViews(holder, position)
        return convertView!!
    }

    inner class ViewHolder(itemView: View) {
        val views = Array<View>(viewsId.size, { i -> itemView.findViewById(viewsId[i]) })
    }

    abstract fun setViews(holder: ViewHolder, position: Int)
}