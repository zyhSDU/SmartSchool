package com.example.administrator.smartschool.util

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.administrator.smartschool.R

/**
 * Created by Administrator on 2018/7/16 0016.
 */

object SpinnerHelper {
    fun setSpinner(spinner: Spinner, context: Context, list: List<String>) {
        spinner.adapter = ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, list)
    }
}
