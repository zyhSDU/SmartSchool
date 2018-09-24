package com.example.administrator.smartschool.ui.ac.abac

import android.view.View
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.bean.BaseBean
import com.example.administrator.smartschool.bean.UserPwd
import com.example.administrator.smartschool.constants.UserConstantsList
import com.example.administrator.smartschool.net.CallUtil
import com.example.administrator.smartschool.ui.ac.Main2Activity
import com.example.administrator.smartschool.ui.ac.ac.MainActivity
import kotlinx.android.synthetic.main.ac_login.*

/**
 * Created by Administrator on 2018/7/18 0018.
 */

class LoginAbAc : BaseAbAc() {
    override val layoutResId: Int
        get() = R.layout.ac_login

    override fun initOnCreate() {
        titleBar.visibility = View.GONE
        val userPwd = UserConstantsList.arrayList[0]
        et_username_ac_login.setText(userPwd.username)
        et_password_ac_login.setText(userPwd.password)

        btn_ac_login.setOnClickListener {
            val username = et_username_ac_login.text.toString()
            val password = et_password_ac_login.text.toString()
            login(username, password)
        }
        btn_register_ac_login.setOnClickListener {
            RegisterAbAc.start(this)
        }
        btn_login_administrator.setOnClickListener {
            login(administrator)
        }
        btn_login_teacher.setOnClickListener {
            login(teacher)
        }
    }

    private fun login(username: String, password: String) {
        CallUtil({
            val baseBean = it.obj as BaseBean
            when (baseBean.code) {
                0 -> {
                    val identity = when {
                        username == teacher.username && password == teacher.password -> 1
                        username == administrator.username && password == administrator.password -> 2
                        else -> 0
                    }
                    Main2Activity.start(this@LoginAbAc, identity)
                    finish()
                }
            }
        }).login(username, password)
    }

    private fun login(userPwd: UserPwd) {
        login(userPwd.username, userPwd.password)
    }

    companion object {
        val teacher = UserConstantsList.teacher
        val administrator = UserConstantsList.administrator
    }
}
