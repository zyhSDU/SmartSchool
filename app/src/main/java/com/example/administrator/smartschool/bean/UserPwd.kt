package com.example.administrator.smartschool.bean

/**
 * Created by Administrator on 2018/3/9 0009.
 */
class UserPwd(val username: String, val password: String){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserPwd

        if (username != other.username) return false
        if (password != other.password) return false

        return true
    }

    override fun hashCode(): Int {
        var result = username.hashCode()
        result = 31 * result + password.hashCode()
        return result
    }
}