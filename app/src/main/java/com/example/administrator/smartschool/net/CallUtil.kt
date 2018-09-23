package com.example.administrator.smartschool.net

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import com.example.administrator.smartschool.bean.*
import com.example.administrator.smartschool.bean.weather.WeatherBean
import com.example.administrator.smartschool.net.NetUtil.Companion.urlGet
import com.example.administrator.smartschool.util.Logger
import com.google.gson.Gson
import okhttp3.Response
import java.util.HashMap
import kotlin.collections.LinkedHashMap
import kotlin.collections.get
import kotlin.collections.set

/**
 * Created by Administrator on 2018/2/2 0002.
 */

class CallUtil(private val initHandleMessage: (msg: Message) -> Unit) {
    private val handler: Handler
    private var message: Message? = null

    init {
        handler = @SuppressLint("HandlerLeak")
        object : Handler() {
            override fun handleMessage(msg: Message) {
                initHandleMessage(msg)
            }
        }
    }

    private fun initInitOnSuccess(initInitInitOnSuccess: () -> Unit) {
        Logger.e("smartCampus", "")
        message = handler.obtainMessage()
        initInitInitOnSuccess()
        handler.sendMessage(message)
    }

    private fun initInitOnSuccess(response: Response, clazz: Class<*>, url: String) {
        initInitOnSuccess {
            val string = response.body()!!.string()
            Logger.e(url, string)
            message!!.obj = gson.fromJson(string, clazz)
        }
    }

    /**
     * 12
     * 用户结束此次报修
     * @param id 报修id
     */
    fun finishRepair(id: Int) {
        val params = HashMap<String, String>()
        params["id"] = id.toString()
        val url = repair_finishRepairUrl
        NetUtil.post(url, params, {
            initInitOnSuccess(it, BaseBean::class.java, url)
        })
    }

    /**
     * 10
     * 管理员受理报修
     * @param id 报修id
     */
    fun dealRepair(id: Int) {
        val params = HashMap<String, String>()
        params["id"] = id.toString()
        val url = repair_dealRepairUrl
        NetUtil.post(url, params, {
            initInitOnSuccess(it, BaseBean::class.java, url)
        })
    }

    /**
     * 9
     * 管理员获取报修列表
     * @param page --  报修列表页码(每页最多20条)
     */
    fun getRepairs(page: Int) {
        val params = HashMap<String, String>()
        params["page"] = page.toString()
        val url = urlGet(repair_getRepairsUrl, params)
        NetUtil.get(url, {
            initInitOnSuccess(it, RepairInfoListBean::class.java, url)
        })
    }

    /**
     * 11
     * 用户获取个人历史报修列表
     * @param page --  报修列表页码(每页最多20条)
     */
    fun getPrivateRepairs(page: Int) {
        val params = HashMap<String, String>()
        params["page"] = page.toString()
        val url = urlGet(repair_getPrivateRepairsUrl, params)
        NetUtil.get(url, {
            initInitOnSuccess(it, RepairInfoListBean::class.java, url)
        })
    }

    /**
     * 8
     * 提交一条报修
     * @param describe（不得超过80个字）
     */
    fun submitRepair(describe: String) {
        val params = HashMap<String, String>()
        params["describe"] = describe
        val url = repair_submitRepairUrl
        NetUtil.post(url, params, {
            initInitOnSuccess(it, BaseBeanWithObject::class.java, url)
        })
    }

    /**
     * @param username 用户填写的用户名
     * @param password 用户填写的密码
     * @param identify 0 学生， 1 教师
     * @param schoolId 一个数字， 测试时只能传过来1， 表示山东大学
     */
    fun register(username: String, password: String, identify: Int, schoolId: Int) {
        val params = HashMap<String, String>()
        params["username"] = username
        params["password"] = password
        params["identify"] = identify.toString()
        params["schoolId"] = schoolId.toString()
        val url = user_registerUrl
        NetUtil.post(url, params, {
            initInitOnSuccess(it, BaseBean::class.java, url)
        })
    }

    /**
     * @param username 用户填写的用户名
     * @param password 用户填写的密码
     */
    fun login(username: String, password: String) {
        val params = HashMap<String, String>()
        params["username"] = username
        params["password"] = password
        val url = user_loginUrl
        NetUtil.post(url, params, {
            initInitOnSuccess(it, BaseBean::class.java, url)
        })
    }

    fun getUserInfo() {
        val url = user_getInfoUrl
        NetUtil.get(url, {
            initInitOnSuccess(it, UserInfoBean::class.java, url)
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
                val linkedHashMap = fromJson.`object`!!
                val stringIntLinkedHashMap: LinkedHashMap<String, Int> = LinkedHashMap()
                linkedHashMap.map {
                    val key = it.key
                    stringIntLinkedHashMap[key.toString()] = linkedHashMap[key].toString().toDouble().toInt()
                }
                message!!.obj = SchoolsBean(fromJson.code, fromJson.message, stringIntLinkedHashMap)
            })
        })
    }

    fun allCampus() {
        val url = campus_allCampusUrl
        NetUtil.get(url, {
            initInitOnSuccess(it, CampusListBean::class.java, url)
        })
    }

    fun busList() {
        val url = bus_busListUrl
        NetUtil.get(url, {
            initInitOnSuccess(it, BusListBean::class.java, url)
        })
    }

    fun busListByPath(from: Int, to: Int) {
        val params = HashMap<String, String>()
        params["from"] = from.toString()
        params["to"] = to.toString()
        val url = urlGet(bus_busListByPathUrl, params)
        NetUtil.get(url, {
            initInitOnSuccess(it, BusListBean::class.java, url)
        })
    }

    fun getWeather(city: String) {
        val params = HashMap<String, String>()
        params["city"] = city
        val url = urlGet(getWeatherUrl, params)
        NetUtil.get(url, {
            initInitOnSuccess(it, WeatherBean::class.java, url)
        })
    }

    /**
     * 13
     * 提交一条认领
     * 	@param zdescribe --  认领描述（不得超过80个字）
     *  @param phone -- 联系方式（不超过11位）
     *  @param picCode -- 图片base64编码后的字符串，若没有图片，传"null" 字符串
     */
    fun lost_submitLost(zdescribe: String, phone: String, picCode: String?) {
        val params = HashMap<String, String>()
        params["zdescribe"] = zdescribe
        params["phone"] = phone
        params["picCode"] = picCode.toString()
        val url = lost_submitLostUrl
        NetUtil.post(url, params, {
            initInitOnSuccess(it, BaseBeanWithObject::class.java, url)
        })
    }

    fun lost_getLostList(page: Int) {
        val params = HashMap<String, String>()
        params["page"] = page.toString()
        val url = urlGet(lost_getLostListUrl, params)
        NetUtil.get(url, {
            initInitOnSuccess(it, LostListBean::class.java, url)
        })
    }

    fun lost_getLostInfo(id: Int) {
        val params = HashMap<String, String>()
        params["id"] = id.toString()
        val url = urlGet(lost_getLostInfoUrl, params)
        NetUtil.get(url, {
            initInitOnSuccess(it, LostBean::class.java, url)
        })
    }

    fun lost_finishLost(id: Int) {
        val params = HashMap<String, String>()
        params["id"] = id.toString()
        val url = lost_finishLostUrl
        NetUtil.post(url, params, {
            initInitOnSuccess(it, BaseBean::class.java, url)
        })
    }

    /**
     * 17
     * 查看本校的约车约自习列表
     * @param page 页码（可选，默认为1）
     * @param type 类型 (可选，默认为1) 0--约车 1--约自习 -1--不筛选
     */
    fun together_list(page: Int, type: Int) {
        val params = HashMap<String, String>()
        params["page"] = page.toString()
        params["type"] = type.toString()
        val url = urlGet(together_listUrl, params)
        NetUtil.get(url, {
            initInitOnSuccess(it, TogetherListBean::class.java, url)
        })
    }

    /**
     * 18
     * 发起一条约车约自习
     * @param num 一共需要的人数（人数不可超过5）
     * @param type 活动类型 0--约车 1--约自习
     * @param zdescribe 活动描述（不超过80个字）
     */
    fun together_start(num: Int, type: Int, zdescribe: String) {
        val params = HashMap<String, String>()
        params["num"] = num.toString()
        params["type"] = type.toString()
        params["zdescribe"] = zdescribe.toString()
        val url = together_startUrl
        NetUtil.post(url, params, {
            initInitOnSuccess(it, BaseBean::class.java, url)
        })
    }

    /**
     * 19
     * 停止一条约车约自习
     * @param toId 约id
     */
    fun together_stop(toId: Int) {
        val params = HashMap<String, String>()
        params["toId"] = toId.toString()
        val url = together_stopUrl
        NetUtil.post(url, params, {
            initInitOnSuccess(it, BaseBean::class.java, url)
        })
    }

    /**
     * 20
     * 参加一条约车约自习的活动
     * @param toId 约id
     * @param info 联系说明，如手机号等(不超过80个字)
     */
    fun together_partTog(toId: Int, info: String) {
        val params = HashMap<String, String>()
        params["toId"] = toId.toString()
        params["info"] = info
        val url = together_partTogUrl
        NetUtil.post(url, params, {
            initInitOnSuccess(it, BaseBean::class.java, url)
        })
    }

    /**
     * 21
     * 查看某活动的参与者信息
     * @param toId 约id
     */
    fun together_getParts(toId: Int) {
        val params = HashMap<String, String>()
        params["toId"] = toId.toString()
        val url = urlGet(together_getPartsUrl, params)
        NetUtil.get(url, {
            initInitOnSuccess(it, TogetherParterListBean::class.java, url)
        })
    }

    /**
     * 22
     * 退出某条参加了的活动
     * @param toId 约id
     */
    fun together_quitTog(toId: Int) {
        val params = HashMap<String, String>()
        params["toId"] = toId.toString()
        val url = together_quitTogUrl
        NetUtil.post(url, params, {
            initInitOnSuccess(it, BaseBean::class.java, url)
        })
    }

    /**
     * 23
     * 发起者移除某个参与活动的人
     * @param toId 约id
     */
    fun together_romveSomeone(toId: Int, userId: Int) {
        val params = HashMap<String, String>()
        params["toId"] = toId.toString()
        params["userId"] = userId.toString()
        val url = together_romveSomeoneUrl
        NetUtil.post(url, params, {
            initInitOnSuccess(it, BaseBean::class.java, url)
        })
    }

    /**
     * 24
     * 查看自己曾经发起的活动
     * @param page 页码（可选，默认为1）
     */
    fun together_getSelfTog(page: Int) {
        val params = HashMap<String, String>()
        params["page"] = page.toString()
        val url = urlGet(together_getSelfTogUrl, params)
        NetUtil.get(url, {
            initInitOnSuccess(it, TogetherListBean::class.java, url)
        })
    }

    /**
     * 25
     * 发起者一场签到
     * @param password 密码
     */
    fun sign_startSign(password: String) {
        val params = HashMap<String, String>()
        params["password"] = password
        val url = sign_startSignUrl
        NetUtil.post(url, params, {
            initInitOnSuccess(it, BaseBeanWithObject::class.java, url)
        })
    }

    /**
     * 26
     * 发起者结束一场签到
     * @param signId 签到id
     */
    fun sign_stopSign(signId: Int) {
        val params = HashMap<String, String>()
        params["signId"] = signId.toString()
        val url = sign_stopSignUrl
        NetUtil.post(url, params, {
            initInitOnSuccess(it, BaseBean::class.java, url)
        })
    }

    /**
     * 27
     * 进行某一次签到
     * @param signId 签到id
     * @param password 密码
     */
    fun sign_partOne(signId: Int, password: String) {
        val params = HashMap<String, String>()
        params["signId"] = signId.toString()
        params["password"] = password
        val url = sign_partOneUrl
        NetUtil.post(url, params, {
            initInitOnSuccess(it, BaseBean::class.java, url)
        })
    }

    /**
     * 28
     * 获取已经签到人的名单
     * @param signId 签到id
     * @param page 页码（可选，默认为1） 每页30条
     */
    fun sign_getSignList(signId: Int, page: Int) {
        val params = HashMap<String, String>()
        params["signId"] = signId.toString()
        params["page"] = page.toString()
        val url = urlGet(sign_getSignListUrl, params)
        NetUtil.get(url, {
            initInitOnSuccess(it, SignerListBean::class.java, url)
        })
    }

    companion object {
        val gson = Gson()

        private const val baseUrl = "http://106.14.212.49/smartCampus"

        private const val userUrl = "$baseUrl/user"
        private const val user_registerUrl = "$userUrl/register"
        private const val user_loginUrl = "$userUrl/login"
        private const val user_getInfoUrl = "$userUrl/getInfor"

        private const val allUniversityUrl = "$baseUrl/university/allUniversity"

        private const val campusUrl = "$baseUrl/campus"
        private const val campus_allCampusUrl = "$campusUrl/allCampus"

        private const val busUrl = "$baseUrl/bus"
        private const val bus_busListUrl = "$busUrl/busList"
        private const val bus_busListByPathUrl = "$busUrl/busListByPath"

        private const val repairUrl = "$baseUrl/repair"
        private const val repair_submitRepairUrl = "$repairUrl/submitRepair"
        private const val repair_getRepairsUrl = "$repairUrl/getRepairs"
        private const val repair_dealRepairUrl = "$repairUrl/dealRepair"
        private const val repair_getPrivateRepairsUrl = "$repairUrl/getPrivateRepairs"
        private const val repair_finishRepairUrl = "$repairUrl/finishRepair"

        private const val lostUrl = "$baseUrl/lost"
        private const val lost_submitLostUrl = "$lostUrl/submitLost"
        private const val lost_getLostListUrl = "$lostUrl/getLostList"
        private const val lost_getLostInfoUrl = "$lostUrl/getLostInfo"
        private const val lost_finishLostUrl = "$lostUrl/finishLost"

        private const val togetherUrl = "$baseUrl/together"
        private const val together_listUrl = "$togetherUrl/list"
        private const val together_startUrl = "$togetherUrl/start"
        private const val together_stopUrl = "$togetherUrl/stop"
        private const val together_partTogUrl = "$togetherUrl/partTog"
        private const val together_getPartsUrl = "$togetherUrl/getParts"
        private const val together_quitTogUrl = "$togetherUrl/quitTog"
        private const val together_romveSomeoneUrl = "$togetherUrl/romveSomeone"
        private const val together_getSelfTogUrl = "$togetherUrl/getSelfTog"

        private const val signUrl = "$baseUrl/sign"
        private const val sign_startSignUrl = "$signUrl/startSign"
        private const val sign_stopSignUrl = "$signUrl/stopSign"
        private const val sign_partOneUrl = "$signUrl/partOne"
        private const val sign_getSignListUrl = "$signUrl/getSignList"

        private const val getWeatherUrl = "https://www.sojson.com/open/api/weather/json.shtml"
    }
}