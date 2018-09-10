package com.example.administrator.smartschool.ui.ac.abac

import android.view.View
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.ui.ac.ac.MainActivity
import com.example.administrator.smartschool.bean.BaseBean
import com.example.administrator.smartschool.bean.UserPwd
import com.example.administrator.smartschool.constants.UserConstantsList
import com.example.administrator.smartschool.util.CallUtil
import kotlinx.android.synthetic.main.ac_login.*

/**
 * Created by Administrator on 2018/7/18 0018.
 */

class LoginAbAc : BaseAbAc() {
    override val layoutResId: Int
        get() = R.layout.ac_login

    var userPwd: UserPwd? = null

    override fun initOnCreate() {
        val userPwd = UserConstantsList.arrayList[0]
        et_username_ac_login.setText(userPwd.username)
        et_password_ac_login.setText(userPwd.password)
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
        userPwd= UserPwd(username,password)
        CallUtil({
            val baseBean = it.obj as BaseBean
            when (baseBean.code) {
                0 -> {
                    if (userPwd== UserConstantsList.administrator)
                        MainActivity.start(this@LoginAbAc, true.toString())
                    finish()
                }
            }
        }).login(username, password)
    }
}
