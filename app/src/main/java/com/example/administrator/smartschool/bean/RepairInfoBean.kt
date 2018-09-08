package com.example.administrator.smartschool.bean

/**
 * Created by Administrator on 2018/9/8 0008.
 */

class RepairInfoBean : BaseBean() {
    var `object`: List<RepairInfo>? = null
    override fun toString(): String {
        return "RepairInfoBean(`object`=$`object`)"
    }
}