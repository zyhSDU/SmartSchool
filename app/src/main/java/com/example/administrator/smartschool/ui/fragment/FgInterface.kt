package com.example.administrator.smartschool.ui.fragment

import android.content.Context
import android.widget.Toast

interface FgInterface {
    fun showToast(context: Context, text: String?) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}