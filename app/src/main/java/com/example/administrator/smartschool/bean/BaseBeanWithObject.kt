package com.example.administrator.smartschool.bean

/**
 * Created by Administrator on 2017/12/7 0007.
 */
class BaseBeanWithObject : BaseBean() {
    var `object`: Any? = null
    override fun toString(): String {
        return "BaseBeanWithObject(`object`=$`object`)"
    }
}