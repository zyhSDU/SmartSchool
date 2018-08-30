package com.example.administrator.smartschool.ac.abac

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import android.view.View

import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.bean.BaseBean
import com.example.administrator.smartschool.util.CallUtil
import kotlinx.android.synthetic.main.ac_register.*

/**
 * Created by Administrator on 2018/7/18 0018.
 */

class RegisterAbAc : BaseAbAc() {
    override val layoutResId: Int
        get() = R.layout.ac_register

    override fun initOnCreate() {
        et_username_ac_register.setText("0")
        et_password_ac_register.setText("0")
        et_identify_ac_register.setText("0")
        et_schoolId_ac_register.setText("0")
    }

    override fun initOnClick(view: View, id: Int) {
        when (id) {
            R.id.btn_ac_register -> {
                val username = et_username_ac_register.text.toString()
                val password = et_password_ac_register.text.toString()
                val identify = et_identify_ac_register.text.toString().toInt()
                val schoolId = et_schoolId_ac_register.text.toString().toInt()
                register(username, password, identify, schoolId)
            }
        }
    }

    private fun register(username: String, password: String, identify: Int, schoolId: Int) {
        startThread{CallUtil(registerHandler).register(username, password, identify, schoolId)}
    }

    private val registerHandler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            val baseBean = msg.obj as BaseBean

            showToast("" + baseBean.code + "==" + baseBean.message)

            when (baseBean.code) {
                0 -> {
                    startActivity(LoginAbAc::class.java)
                    finish()
                }
            }
        }
    }
}
