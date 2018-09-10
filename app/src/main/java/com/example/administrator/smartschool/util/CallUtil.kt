package com.example.administrator.smartschool.util

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import com.example.administrator.smartschool.bean.*
import com.example.administrator.smartschool.bean.weather.WeatherBean
import com.example.administrator.tiaozhanbei.util.NetUtil
import com.google.gson.Gson
import okhttp3.Response
import java.io.IOException
import java.util.HashMap
import kotlin.collections.LinkedHashMap
import kotlin.collections.get
import kotlin.collections.set

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

        private const val allUniversityUrl = "$baseUrl/university/allUniversity"

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

    private fun initInitOnSuccess(initInitInitOnSuccess: () -> Unit) {
        message = handler.obtainMessage()
        try {
            initInitInitOnSuccess()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            handler.sendMessage(message)
        }
    }

    private fun initInitOnSuccess(response: Response, clazz: Class<*>, objTag: String = this.javaClass.simpleName) {
        initInitOnSuccess {
            val string = response.body()!!.string()
            message!!.obj = gson.fromJson(string, clazz)
            Logger.e(objTag, string!!)
        }
    }

    private fun getLatterChars(string: String): String = string.substring(baseUrl.length)

    /**
     * @param id 报修id
     */
    fun finishRepair(id: Int) {
        val url = finishRepairUrl
        NetUtil.get("$url?id=$id", {
            initInitOnSuccess(it, BaseBean::class.java, getLatterChars(url))
        })
    }

    /**
     * @param id 报修id
     */
    fun dealRepair(id: Int) {
        val url = dealRepairUrl
        NetUtil.get("$dealRepairUrl?id=$id", {
            initInitOnSuccess(it, BaseBean::class.java,getLatterChars(url))
        })
    }

    /**
     * @param page --  报修列表页码(每页最多20条)
     */
    fun getRepairs(page: Int) {
        val url = getRepairsUrl
        NetUtil.get("$url?page=$page", {
            initInitOnSuccess(it, RepairInfoBean::class.java,getLatterChars(url))
        })
    }

    /**
     * @param page --  报修列表页码(每页最多20条)
     */
    fun getPrivateRepairs(page: Int) {
        val url = getPrivateRepairsUrl
        NetUtil.get("$url?page=$page", {
            initInitOnSuccess(it, RepairInfoBean::class.java,getLatterChars(url))
        })
    }

    /**
     * @param describe（不得超过80个字）
     */
    fun submitRepair(describe: String) {
        val params = HashMap<String, String>()
        params["describe"] = describe
        val url = submitRepairUrl
        NetUtil.post(url, params, {
            initInitOnSuccess(it, BaseBeanWithObject::class.java,getLatterChars(url))
        })
    }

    fun register(username: String, password: String, identify: Int, schoolId: Int) {
        val params = HashMap<String, String>()
        params["username"] = username
        params["password"] = password
        params["identify"] = "$identify"
        params["schoolId"] = "$schoolId"
        val url = registerUrl
        NetUtil.post(url, params, {
            initInitOnSuccess(it, BaseBean::class.java,getLatterChars(url))
        })
    }

    fun login(username: String, password: String) {
        val params = HashMap<String, String>()
        params["username"] = username
        params["password"] = password
        val url = loginUrl
        NetUtil.post(url, params, {
            initInitOnSuccess(it, BaseBean::class.java, getLatterChars(url))
        })
    }

    fun getUserInfo() {
        val url = getUserInfoUrl
        NetUtil.get(url, {
            initInitOnSuccess(it, UserInfoBean::class.java,getLatterChars(url))
        })
    }

    /**
     * 例外
     */
    fun allUniversity() {
        NetUtil.get(allUniversityUrl, {
            initInitOnSuccess({
                val string = it.body()!!.string()
                val fromJson = gson.fromJson(string, LinkedTreeMapBean::class.java)
                val linkedHashMap: LinkedHashMap<*, *> = fromJson.`object`!!
                val stringIntLinkedHashMap: LinkedHashMap<String, Int> = LinkedHashMap()
                for (i in linkedHashMap.keys) {
                    stringIntLinkedHashMap[i.toString()] = linkedHashMap[i].toString().toDouble().toInt()
                }
                message!!.obj = SchoolsBean(fromJson.code, fromJson.message, stringIntLinkedHashMap)
            })
        })
    }

    fun allCampus() {
        val url = allCampusUrl
        NetUtil.get(url, {
            initInitOnSuccess(it, CampusBean::class.java,getLatterChars(url))
        })
    }

    fun busList() {
        val url = busListUrl
        NetUtil.get(url, {
            initInitOnSuccess(it, BusBean::class.java,getLatterChars(url))
        })
    }

    fun busListByPath(from: Int, to: Int) {
        val url = busListByPathUrl
        NetUtil.get("$url?from=$from&to=$to", {
            initInitOnSuccess(it, BusBean::class.java,getLatterChars(url))
        })
    }

    fun getWeather(city: String) {
        val url = getWeatherUrl
        NetUtil.get("$url?city=$city", {
            initInitOnSuccess(it, WeatherBean::class.java,getLatterChars(url))
        })
    }
}