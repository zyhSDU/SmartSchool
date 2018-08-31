package com.example.administrator.smartschool.bean.weather

/**
 * Created by Administrator on 2018/9/1 0001.
 */

class Data {
    var wendu: String? = null
    var ganmao: String? = null
    var forecast: List<Forecast>? = null
    var yesterday: Yesterday? = null
    var aqi: String? = null
    var city: String? = null
    override fun toString(): String {
        return "Data(wendu=$wendu, ganmao=$ganmao, forecast=$forecast, yesterday=$yesterday, aqi=$aqi, city=$city)"
    }
}