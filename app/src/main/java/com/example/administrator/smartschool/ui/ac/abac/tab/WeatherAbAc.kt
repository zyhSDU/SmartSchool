package com.example.administrator.smartschool.ui.ac.abac.tab

import android.annotation.SuppressLint
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.bean.weather.WeatherBean
import com.example.administrator.smartschool.net.CallUtil
import com.example.administrator.smartschool.ui.ac.abac.BaseAbAc
import com.example.administrator.smartschool.util.Logger
import kotlinx.android.synthetic.main.ac_weather.*

/**
 * Created by Administrator on 2018/7/18 0018.
 */

class WeatherAbAc : BaseAbAc() {
    override val layoutResId: Int
        get() = R.layout.ac_weather

    override fun initOnCreate() {
        et_city_weather.setText("济南")
        btn_searcher_weather.setOnClickListener {
            getWeather(et_city_weather.text.toString())
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getWeather(city: String) {
        CallUtil(initHandleMessage = { msg ->
            val weatherBean = msg.obj as WeatherBean
            when (weatherBean.status) {
                200 -> {
                    val data = weatherBean.data!!
                    val forecast = data.forecast!!
                    val string = StringBuffer("温度：${data.wendu}\n" +
                            "${data.ganmao}\n" +
                            "预报：\n\n")
                    forecast.map {
                        string.append(it).append("\n\n")
                    }
                    tv_show_weather.text=string.toString()
                }
            }
        }).getWeather(city)
    }
}
