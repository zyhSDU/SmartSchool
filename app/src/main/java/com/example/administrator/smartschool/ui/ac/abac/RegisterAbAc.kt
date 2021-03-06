package com.example.administrator.smartschool.ui.ac.abac

import android.content.Context
import android.content.Intent
import android.view.View
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.bean.BaseBean
import com.example.administrator.smartschool.bean.SchoolsBean
import com.example.administrator.smartschool.net.CallUtil
import com.example.administrator.smartschool.util.AbTitleBarHelper
import com.example.administrator.smartschool.util.SpinnerHelper
import kotlinx.android.synthetic.main.ac_register.*

/**
 * Created by Administrator on 2018/7/18 0018.
 */

class RegisterAbAc : BaseAbAc() {
    override val layoutResId: Int
        get() = R.layout.ac_register
    lateinit var linkedHashMap: LinkedHashMap<String, Int>
    lateinit var strings: List<String>
    override fun initOnCreate() {
        AbTitleBarHelper.initAbTitleBar4(mAbTitleBar, this, titleBarTitle)

        titleBar.visibility=View.GONE
        allUniversity()
        et_username_ac_register.setText("2")
        et_password_ac_register.setText("2")
        et_identify_ac_register.setText("0")
    }

    override fun initOnClick(view: View, id: Int) {
        when (id) {
            R.id.btn_ac_register -> {
                val username = et_username_ac_register.text.toString()
                val password = et_password_ac_register.text.toString()
                val selectedItemId = spinner_school_ac_register.selectedItemId.toInt()
                val schoolId = linkedHashMap[strings[selectedItemId]]!!
                val identify = et_identify_ac_register.text.toString().toInt()
                register(username, password, identify, schoolId)
            }
        }
    }

    private fun register(username: String, password: String, identify: Int, schoolId: Int) {
        CallUtil(initHandleMessage = { msg ->
            val baseBean = msg.obj as BaseBean
            when (baseBean.code) {
                0 -> {
                    startActivity(LoginAbAc::class.java)
                    finish()
                }
            }
        }).register(username, password, identify, schoolId)
    }

    private fun allUniversity() {
        CallUtil({
            val schoolsBean = it.obj as SchoolsBean
            when (schoolsBean.code) {
                0 -> {
                    linkedHashMap = schoolsBean.`object`!!
                    val keys = linkedHashMap.keys
                    strings = keys.map { it }
                    SpinnerHelper.setSpinner(
                            spinner_school_ac_register,
                            this@RegisterAbAc,
                            strings
                    )
                    spinner_school_ac_register.setSelection(1)
                }
            }
        }).allUniversity()
    }

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, RegisterAbAc::class.java)
            context.startActivity(starter)
        }
    }
}
