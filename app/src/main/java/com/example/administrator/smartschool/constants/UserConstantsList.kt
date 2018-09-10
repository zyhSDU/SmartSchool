package com.example.administrator.smartschool.constants

import com.example.administrator.smartschool.bean.UserPwd
import java.util.ArrayList

object UserConstantsList {
    val administrator = UserPwd("zyh", "123456")
    val arrayList = ArrayList<UserPwd>()

    init {
        arrayList.add(UserPwd("2", "2"))
    }
}
