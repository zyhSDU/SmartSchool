package com.example.administrator.smartschool.util

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import com.example.administrator.smartschool.bean.*
import com.example.administrator.smartschool.bean.weather.WeatherBean
import com.example.administrator.smartschool.temp.Temp
import com.example.administrator.tiaozhanbei.util.NetUtil
import com.google.gson.Gson
import okhttp3.Response
import java.io.IOException
import java.util.HashMap

/**
 * Created by Administrator on 2018/2/2 0002.
 */

class CallUtil(private val initHandleMessage: (msg: Message) -> Unit) {
    private var handler: Handler
    private var message: Message? = null

    init {
        handler = @SuppressLint("HandlerLeak")
        object : Handler() {
            override fun handleMessage(msg: Message) {
                initHandleMessage(msg)
            }
        }
    }

    companion object {
        private val gson: Gson = Gson()

        private const val baseUrl = "http://106.14.212.49/smartCampus"

        private const val userUrl = "$baseUrl/user"
        private const val registerUrl = "$userUrl/register/"
        private const val loginUrl = "$userUrl/login/"
        private const val getUserInfoUrl = "$userUrl/getInfor"

        private const val allUniversity = "$baseUrl/university/allUniversity"

        private const val campusUrl = "$baseUrl/campus"
        private const val allCampusUrl = "$campusUrl/allCampus"

        private const val busUrl = "$baseUrl/bus"
        private const val busListUrl = "$busUrl/busList"
        private const val busListByPathUrl = "$busUrl/busListByPath"

        private const val repairUrl = "$baseUrl/repair"
        private const val submitRepairUrl = "$repairUrl/submitRepair/"
        private const val getRepairsUrl = "$repairUrl/getRepairs/"
        private const val getPrivateRepairsUrl = "$repairUrl/getPrivateRepairs/"
        private const val dealRepairUrl = "$repairUrl/dealRepair/"
        private const val finishRepairUrl = "$repairUrl/finishRepair/"

        private const val getWeatherUrl = "https://www.sojson.com/open/api/weather/json.shtml"
    }

    /**
     * @param id 报修id
     */
    fun finishRepair(id: Int) {
        NetUtil.get("$finishRepairUrl?id=$id", {
            message = handler.obtainMessage()
            try {
                val string = it.body()!!.string()
                message!!.obj = gson.fromJson(string, BaseBean::class.java)
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                handler.sendMessage(message)
            }
        })
    }

    /**
     * @param id 报修id
     */
    fun dealRepair(id: Int) {
        NetUtil.get("$dealRepairUrl?id=$id", {
            message = handler.obtainMessage()
            try {
                val string = it.body()!!.string()
                message!!.obj = gson.fromJson(string, BaseBean::class.java)
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                handler.sendMessage(message)
            }
        })
    }

    /**
     * @param page --  报修列表页码(每页最多20条)
     */
    fun getRepairs(page: Int) {
        NetUtil.get("$getRepairsUrl?page=$page", {
            message = handler.obtainMessage()
            try {
                val string = it.body()!!.string()
                message!!.obj = gson.fromJson(string, RepairInfoBean::class.java)
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                handler.sendMessage(message)
            }
        })
    }

    /**
     * @param page --  报修列表页码(每页最多20条)
     */
    fun getPrivateRepairs(page: Int) {
        NetUtil.get("$getPrivateRepairsUrl?page=$page", {
            message = handler.obtainMessage()
            try {
                val string = it.body()!!.string()
                message!!.obj = gson.fromJson(string, RepairInfoBean::class.java)
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                handler.sendMessage(message)
            }
        })
    }

    /**
     * @param describe（不得超过80个字）
     */
    fun submitRepair(describe: String) {
        val params = HashMap<String, String>()
        params["describe"] = describe

        try {
            NetUtil.post(submitRepairUrl, params, {
                message = handler.obtainMessage()
                try {
                    message!!.obj = gson.fromJson(it.body()!!.string(), BaseBeanWithObject::class.java)
                } catch (e: IOException) {
                    e.printStackTrace()
                } finally {
                    handler.sendMessage(message)
                }
            })
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun register(username: String, password: String, identify: Int, schoolId: Int) {
        val params = HashMap<String, String>()
        params["username"] = username
        params["password"] = password
        params["identify"] = "$identify"
        params["schoolId"] = "$schoolId"
        try {
            NetUtil.post(registerUrl, params, {
                message = handler.obtainMessage()
                try {
                    message!!.obj = gson.fromJson(it.body()!!.string(), BaseBean::class.java)
                } catch (e: IOException) {
                    e.printStackTrace()
                } finally {
                    handler.sendMessage(message)
                }
            })
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun login(username: String, password: String) {
        val params = HashMap<String, String>()
        params["username"] = username
        params["password"] = password
        try {
            NetUtil.post(loginUrl, params, {
                message = handler.obtainMessage()
                try {
                    message!!.obj = gson.fromJson(it.body()!!.string(), BaseBean::class.java)
                } catch (e: IOException) {
                    e.printStackTrace()
                } finally {
                    handler.sendMessage(message)
                }
            })
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun getUserInfo() {
        NetUtil.get(getUserInfoUrl, {
            message = handler.obtainMessage()
            try {
                val string = it.body()!!.string()
                Logger.e("getUserInfoUrl", string)
                message!!.obj = gson.fromJson(string, UserInfoBean::class.java)
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                handler.sendMessage(message)
            }
        })
    }

    fun allUniversity() {
        NetUtil.get(allUniversity, {
            message = handler.obtainMessage()
            try {
                val string = it.body()!!.string()
                val fromJson = gson.fromJson(string, LinkedTreeMapBean::class.java)
                val linkedHashMap: LinkedHashMap<*, *> = fromJson.`object`!!
                val stringIntLinkedHashMap: LinkedHashMap<String, Int> = LinkedHashMap()
                for (i in linkedHashMap.keys) {
                    stringIntLinkedHashMap[i.toString()] = linkedHashMap[i].toString().toDouble().toInt()
                }
                message!!.obj = SchoolsBean(fromJson.code, fromJson.message, stringIntLinkedHashMap)
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                handler.sendMessage(message)
            }
        })
    }

    fun allCampus() {
        NetUtil.get(allCampusUrl, {
            message = handler.obtainMessage()
            try {
                val string = it.body()!!.string()
                message!!.obj = gson.fromJson(string, CampusBean::class.java)
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                handler.sendMessage(message)
            }
        })
    }

    fun busList() {
        NetUtil.get(busListUrl, {
            message = handler.obtainMessage()
            try {
                val string = it.body()!!.string()
                message!!.obj = gson.fromJson(string, BusBean::class.java)
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                handler.sendMessage(message)
            }
        })
    }

    fun busListByPath(from: Int, to: Int) {
        NetUtil.get("$busListByPathUrl?from=$from&to=$to", {
            message = handler.obtainMessage()
            try {
                val string = it.body()!!.string()
                message!!.obj = gson.fromJson(string, BusBean::class.java)
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                handler.sendMessage(message)
            }
        })
    }

    fun getWeather(city: String) {
        NetUtil.get("$getWeatherUrl?city=$city", {
            message = handler.obtainMessage()
            try {
                val string = it.body()!!.string()
                message!!.obj = gson.fromJson(string, WeatherBean::class.java)
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                handler.sendMessage(message)
            }
        })
    }
}