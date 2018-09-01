package com.example.administrator.smartschool.bean

/**
 * Created by Administrator on 2018/8/31 0031.
 */

class SchoolsBean : BaseBean{
    var `object`: LinkedHashMap<String, Int>? = null

    constructor(code:Int,message:String?,linkedHashMap: LinkedHashMap<String,Int>){
        this.code=code
        this.message=message
        this.`object`=linkedHashMap
    }
}
