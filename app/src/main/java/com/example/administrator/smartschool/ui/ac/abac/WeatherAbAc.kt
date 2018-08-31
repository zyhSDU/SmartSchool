package com.example.administrator.smartschool.ui.ac.abac

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import android.view.View
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.bean.weather.WeatherBean
import com.example.administrator.smartschool.util.CallUtil
import com.example.administrator.smartschool.util.Logger
import kotlinx.android.synthetic.main.ac_weather.*

/**
 * Created by Administrator on 2018/7/18 0018.
 */

class WeatherAbAc : BaseAbAc() {
    override val layoutResId: Int
        get() = R.layout.ac_weather

    override fun initOnCreate() {
        et_city_weather.setText("云霄县")
        btn_searcher_weather.performClick()
    }

    override fun initOnClick(view: View, id: Int) {
        getWeather(et_city_weather.text.toString())
    }

    private fun getWeather(city: String) {
        startThread {
            CallUtil(getWeatherHandler).getWeather(city)
        }
    }

    private val getWeatherHandler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            val weatherBean = msg.obj as WeatherBean

            showToast("" + weatherBean.status + "==" + weatherBean.message)

            when (weatherBean.status) {
                200 -> {
                    val toString = weatherBean.toString()
                    Logger.e(this, toString)
                    tv_show_weather.text = toString
                }
            }
        }
    }
}
