package com.example.administrator.smartschool.util

import android.os.Handler
import android.os.Message
import com.example.administrator.smartschool.bean.*
import com.example.administrator.smartschool.bean.weather.WeatherBean
import com.example.administrator.tiaozhanbei.util.NetUtil
import com.google.gson.Gson
import okhttp3.Response
import java.io.IOException
import java.util.HashMap

/**
 * Created by Administrator on 2018/2/2 0002.
 */

class CallUtil(private val handler: Handler) {

    private var message: Message? = null

    companion object {
        private val gson: Gson = Gson()

        private const val baseUrl = "http://106.14.212.49/smartCampus"

        private const val userUrl = "$baseUrl/user"
        private const val registerUrl = "$userUrl/register/"
        private const val loginUrl = "$userUrl/login/"
        private const val getUserInfoUrl = "$userUrl/getInfor"
        private const val allUniversity = "$baseUrl/university/allUniversity"

        private const val getWeatherUrl = "https://www.sojson.com/open/api/weather/json.shtml"

    }

    fun register(username: String, password: String,identify: Int, schoolId: Int) {
        val callBackForResult = object : NetUtil.CallBackForResult {
            override fun onFailure(e: IOException) {}

            override fun onSuccess(response: Response) {
                message = handler.obtainMessage()
                try {
                    message!!.obj = gson.fromJson(response.body()!!.string(), BaseBean::class.java)
                } catch (e: IOException) {
                    e.printStackTrace()
                } finally {
                    handler.sendMessage(message)
                }
            }
        }
        val params = HashMap<String, String>()
        params["username"] = username
        params["password"] = password
        params["identify"] = "$identify"
        params["schoolId"] = "$schoolId"

        try {
            NetUtil.post(registerUrl, params, callBackForResult)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun login(username: String, password: String) {
        val callBackForResult = object : NetUtil.CallBackForResult {
            override fun onFailure(e: IOException) {}

            override fun onSuccess(response: Response) {
                message = handler.obtainMessage()
                try {
                    message!!.obj = gson.fromJson(response.body()!!.string(), BaseBean::class.java)
                } catch (e: IOException) {
                    e.printStackTrace()
                } finally {
                    handler.sendMessage(message)
                }
            }
        }
        val params = HashMap<String, String>()
        params["username"] = username
        params["password"] = password

        try {
            NetUtil.post(loginUrl, params, callBackForResult)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun getUserInfo() {
        val callBackForResult = object : NetUtil.CallBackForResult {
            override fun onFailure(e: IOException) {}

            override fun onSuccess(response: Response) {
                message = handler.obtainMessage()
                try {
                    val string = response.body()!!.string()
                    Logger.e("getUserInfoUrl", string)
                    message!!.obj = gson.fromJson(string, UserInfoBean::class.java)
                } catch (e: IOException) {
                    e.printStackTrace()
                } finally {
                    handler.sendMessage(message)
                }
            }
        }
        NetUtil[getUserInfoUrl, callBackForResult]
    }

    fun allUniversity() {
        val callBackForResult = object : NetUtil.CallBackForResult {
            override fun onFailure(e: IOException) {}

            override fun onSuccess(response: Response) {
                message = handler.obtainMessage()
                try {
                    val string = response.body()!!.string()
                    Logger.e("allUniversity", string)
                    val fromJson = gson.fromJson(string, LinkedTreeMapBean::class.java)
                    val linkedHashMap: LinkedHashMap<*, *> = fromJson.`object`!!
                    val stringIntLinkedHashMap: LinkedHashMap<String, Int> = LinkedHashMap()
                    for (i in linkedHashMap.keys) {
                        stringIntLinkedHashMap[i.toString()]=linkedHashMap[i].toString().toDouble().toInt()
                    }
                    message!!.obj = SchoolsBean(fromJson.code,fromJson.message,stringIntLinkedHashMap)

                } catch (e: IOException) {
                    e.printStackTrace()
                } finally {
                    handler.sendMessage(message)
                }
            }
        }
        NetUtil[allUniversity, callBackForResult]
    }

    fun getWeather(city: String) {
        val callBackForResult = object : NetUtil.CallBackForResult {
            override fun onFailure(e: IOException) {}

            override fun onSuccess(response: Response) {
                message = handler.obtainMessage()
                try {
                    val string = response.body()!!.string()
                    message!!.obj = gson.fromJson(string, WeatherBean::class.java)
                } catch (e: IOException) {
                    e.printStackTrace()
                } finally {
                    handler.sendMessage(message)
                }
            }
        }
        NetUtil["$getWeatherUrl?city=$city", callBackForResult]
    }
}