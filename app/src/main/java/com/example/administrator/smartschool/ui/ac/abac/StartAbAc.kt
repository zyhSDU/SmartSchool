package com.example.administrator.smartschool.ui.ac.abac

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import android.view.View
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.bean.BaseBean
import com.example.administrator.smartschool.ui.ac.abac.tab.WeatherAbAc
import com.example.administrator.smartschool.ui.ac.ac.MainActivity
import com.example.administrator.smartschool.util.CallUtil

/**
 * Created by Administrator on 2018/7/18 0018.
 */

class StartAbAc : BaseAbAc() {
    override val layoutResId: Int
        get() = R.layout.ac_start

    override fun initOnCreate() {
//        btn_login.performClick()
    }

    override fun initOnClick(view: View, id: Int) {
        when (id) {
            R.id.btn_login -> {
                startActivity(LoginAbAc::class.java)
            }
            R.id.btn_login_administrator -> {
                login("zyh", "123456")
            }
            R.id.btn_register -> {
                startActivity(RegisterAbAc::class.java)
            }
            R.id.btn_weather -> {
                startActivity(WeatherAbAc::class.java)
            }
            R.id.btn_main -> {
                startActivity(MainActivity::class.java)
            }
        }
    }

    private fun login(username: String, password: String) {
        CallUtil(initHandleMessage = { msg ->
            val baseBean = msg.obj as BaseBean

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
