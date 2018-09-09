package com.example.administrator.smartschool.ui.ac.abac

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import android.view.View
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.ui.ac.ac.MainActivity
import com.example.administrator.smartschool.bean.BaseBean
import com.example.administrator.smartschool.util.CallUtil
import kotlinx.android.synthetic.main.ac_login.*

/**
 * Created by Administrator on 2018/7/18 0018.
 */

class LoginAbAc : BaseAbAc() {
    override val layoutResId: Int
        get() = R.layout.ac_login

    override fun initOnCreate() {
        et_username_ac_login.setText("2")
        et_password_ac_login.setText("2")
//        btn_ac_login.performClick()
    }

    override fun initOnClick(view: View, id: Int) {
        when (id) {
            R.id.btn_ac_login -> {
                val username = et_username_ac_login.text.toString()
                val password = et_password_ac_login.text.toString()
                login(username, password)
            }
        }
    }

    private fun login(username: String, password: String) {
        CallUtil({
            val baseBean = it.obj as BaseBean

            showToast("" + baseBean.code + "==" + baseBean.message)

            when (baseBean.code) {
                0 -> {
                    startActivity(MainActivity::class.java)
                    finish()
                }
            }
        }).login(username, password)
    }
}
