package com.example.administrator.smartschool.ui.fragment.v4

import android.widget.TextView
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.bean.UserInfoBean
import com.example.administrator.smartschool.net.CallUtil

/**
 * Created by zhangYuHan on 2017/8/2.
 */

class BottomFragment3 : BaseFragment() {
    override val layoutId: Int
        get() = R.layout.fr_bottom_main_3
    lateinit var tvs: List<TextView>
    override fun initView() {
        val ints = arrayOf(R.id.tv_identify_bottom_fg_setting, R.id.tv_school_bottom_fg_setting, R.id.tv_username_bottom_fg_setting)
        tvs = ints.map { rootView.findViewById<TextView>(it) }
        getUserInfo()
    }

    private fun getUserInfo() {
        CallUtil({
            val userInfoBean = it.obj as UserInfoBean
            when (userInfoBean.code) {
                0 -> {
                    val userInfo = userInfoBean.`object`!!
                    tvs[0].text = userInfo.identify
                    tvs[1].text = userInfo.school!!.name
                    tvs[2].text = userInfo.username
                }
            }
        }).getUserInfo()
    }
}
