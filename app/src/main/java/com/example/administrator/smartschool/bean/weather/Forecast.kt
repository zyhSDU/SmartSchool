package com.example.administrator.smartschool.bean.weather

/**
 * Created by Administrator on 2018/9/1 0001.
 */
class Forecast {
    var fengxiang: String? = null
    var fengli: String? = null
    var high: String? = null
    var type: String? = null
    var low: String? = null
    var date: String? = null
    override fun toString(): String {
        return "$date：$type，风向：$fengxiang, 风力：$fengli\n  $high, $low"
    }
}
