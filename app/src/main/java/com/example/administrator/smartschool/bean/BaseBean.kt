package com.example.administrator.smartschool.bean

/**
 * Created by Administrator on 2017/12/7 0007.
 */
open class BaseBean(
        val code: Int = 0,
        val message: String? = null
) {
    override fun toString(): String {
        return "LoginInfo(code=$code, message=$message)"
    }
}