package com.example.administrator.smartschool.ui.ac.abac

import android.view.View

interface AbAcInterface :View.OnClickListener{

    val layoutResId: Int

    fun initOnCreate() {}

    fun initOnClick(view: View, id: Int=view.id) {}

    override fun onClick(v: View?) {
        if (v != null) {
            initOnClick(v)
        }
    }
}