package com.example.administrator.smartschool.bean

/**
 * Created by Administrator on 2017/12/7 0007.
 */
class BaseBeanWithId {
    val code: Int = 0
    val message: String? = null
    val `object`: Int? = null

    override fun toString(): String {
        return "LoginInfo(code=$code, message=$message, message=$message)"
    }
}