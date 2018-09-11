package com.example.administrator.smartschool.bean

/**
 * Created by Administrator on 2018/8/31 0031.
 */

class SchoolsBean(code: Int, message: String?, linkedHashMap: LinkedHashMap<String, Int>) : BaseBean(code, message) {
    var `object`: LinkedHashMap<String, Int>? = linkedHashMap
}
