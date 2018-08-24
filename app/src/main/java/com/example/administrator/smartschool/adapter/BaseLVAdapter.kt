package com.example.administrator.smartschool.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

import com.ab.global.AbMenuItem
import com.example.administrator.smartschool.R

/**
 * Created by Administrator on 2018/6/9 0009.
 */

class BaseLVAdapter(private val context: Context, private val list: List<AbMenuItem>, private val itemResource: Int) : BaseAdapter() {

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
            convertView = LayoutInflater.from(context).inflate(itemResource, null) //定义一个视图对象View...
            holder = ViewHolder()
            holder.itemText = convertView!!.findViewById(R.id.pop_item) //在当前的View中获取ListView的ID..
            convertView.tag = holder//设置标志..
        } else {
            holder = convertView.tag as ViewHolder//这里可以直接获取标志，对holder进行复用，减少了内存的分配和开销...
        }
        val item = list[position]
        holder.itemText!!.text = item.text //为每一个Item中的TextView设置值...

        return convertView
    }

    internal class ViewHolder {
        var itemText: TextView? = null
    }
}