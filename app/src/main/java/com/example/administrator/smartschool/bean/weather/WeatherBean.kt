package com.example.administrator.smartschool.bean.weather

/**
 * Created by Administrator on 2018/9/1 0001.
 */

class WeatherBean {
    var status: Int = 0
    var data: Data? = null
    var message: String? = null
    override fun toString(): String {
        return "WeatherBean(status=$status, data=$data, message=$message)"
    }
}
