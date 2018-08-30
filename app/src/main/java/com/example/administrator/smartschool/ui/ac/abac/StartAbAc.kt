package com.example.administrator.smartschool.ui.ac.abac

import android.content.Intent
import android.view.View
import com.example.administrator.smartschool.R

/**
 * Created by Administrator on 2018/7/18 0018.
 */

class StartAbAc : BaseAbAc() {
    override val layoutResId: Int
        get() = R.layout.ac_start

    override fun initOnClick(view: View, id: Int) {
        when(id){
            R.id.btn_login->{
                easyStartActivity(LoginAbAc::class.java)
            }
            R.id.btn_register->{
                easyStartActivity(RegisterAbAc::class.java)
            }
        }
    }

    private fun easyStartActivity(clazz: Class<*>) {
        showToast(clazz.simpleName)
        startActivity(Intent(this, clazz))
    }
}
