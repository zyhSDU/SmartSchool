package com.example.administrator.smartschool.util

import android.content.Context
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.administrator.smartschool.R

/**
 * Created by Administrator on 2018/7/16 0016.
 */

object SpinnerHelper {
    fun setSpinner(spinner: Spinner, context: Context, list: List<String>,layoutId:Int= R.layout.view_spinner) {
//        spinner.adapter = ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, list)
        spinner.adapter = ArrayAdapter(context, layoutId, list)
    }

    interface SpinnerOnItemSelectedListener : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {
        }
    }
}
