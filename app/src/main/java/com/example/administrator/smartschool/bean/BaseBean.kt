package com.example.administrator.smartschool.bean

/**
 * Created by Administrator on 2017/12/7 0007.
 */
open class BaseBean(
        var code: Int = 0,
        var message: String? = null
) {
    override fun toString(): String {
        return "LoginInfo(code=$code, message=$message)"
    }
}