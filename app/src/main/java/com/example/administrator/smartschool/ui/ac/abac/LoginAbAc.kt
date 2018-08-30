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
        et_username_ac_login.setText("1")
        et_password_ac_login.setText("1")
    }

    override fun initOnClick(view: View, id: Int) {
        when (id) {
            R.id.btn_ac_login -> {
                val username = et_username_ac_login.text.toString()
                val password = et_password_ac_login.text.toString()
                register(username, password)
            }
        }
    }

    private fun register(username: String, password: String) {
        startThread {
            CallUtil(loginHandler).login(username, password)
        }
    }

    private val loginHandler= @SuppressLint("HandlerLeak")
    object :Handler(){
        override fun handleMessage(msg: Message) {
            val baseBean = msg.obj as BaseBean

            showToast("" + baseBean.code + "==" + baseBean.message)

            when (baseBean.code) {
                0 -> {
                    startActivity(MainActivity::class.java)
                    finish()
                }
            }
        }
    }
}
