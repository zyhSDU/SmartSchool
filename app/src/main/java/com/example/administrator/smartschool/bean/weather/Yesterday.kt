package com.example.administrator.smartschool.bean.weather

/**
 * Created by Administrator on 2018/9/1 0001.
 */

class Yesterday {
    var fl: String? = null
    var fx: String? = null
    var high: String? = null
    var type: String? = null
    var low: String? = null
    var date: String? = null
    override fun toString(): String {
        return "Yesterday(fl=$fl, fx=$fx, high=$high, type=$type, low=$low, date=$date)"
    }
}
