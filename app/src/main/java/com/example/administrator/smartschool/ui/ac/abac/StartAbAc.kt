package com.example.administrator.smartschool.ui.ac.abac

import android.view.View
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.ui.ac.ac.MainActivity
import kotlinx.android.synthetic.main.ac_start.*

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
}
