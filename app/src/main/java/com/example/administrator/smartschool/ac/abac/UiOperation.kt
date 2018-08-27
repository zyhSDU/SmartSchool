package com.example.administrator.smartschool.ac.abac

import android.view.View

interface UiOperation :View.OnClickListener{

    val layoutResId: Int

    fun initOnCreate() {}

    fun initOnClick(view: View, id: Int=view.id) {}

    override fun onClick(v: View?) {
        if (v != null) {
            initOnClick(v,v.id)
        }
    }
}