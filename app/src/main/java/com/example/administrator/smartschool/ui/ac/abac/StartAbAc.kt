package com.example.administrator.smartschool.ui.ac.abac

import android.view.View
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.bean.BaseBean
import com.example.administrator.smartschool.constants.UserConstantsList
import com.example.administrator.smartschool.net.CallUtil
import com.example.administrator.smartschool.ui.ac.abac.tab.WeatherAbAc
import com.example.administrator.smartschool.ui.ac.ac.MainActivity

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
                val userPwd = UserConstantsList.administrator
                login(userPwd.username, userPwd.password,2)
            }
            R.id.btn_login_teacher -> {
                val userPwd = UserConstantsList.teacher
                login(userPwd.username, userPwd.password,1)
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

    private fun login(username: String, password: String,identity:Int) {
        CallUtil({
            val baseBean = it.obj as BaseBean
            when (baseBean.code) {
                0 -> {
                    MainActivity.start(this@StartAbAc, identity)
                    finish()
                }
            }
        }).login(username, password)
    }
}
