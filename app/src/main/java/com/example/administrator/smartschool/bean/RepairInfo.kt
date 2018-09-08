package com.example.administrator.smartschool.bean

/**
 * Created by Administrator on 2018/9/8 0008.
 */

class RepairInfo {
    var id: Int = 0
    var schoolId: Int = 0
    var zdescribe: String? = null
    var studentId: Int = 0
    var adminId: Int = 0
    var status: Int = 0
    var reportTime: String? = null
    override fun toString(): String {
        return "RepairInfo(id=$id, schoolId=$schoolId, zdescribe=$zdescribe, studentId=$studentId, adminId=$adminId, status=$status, reportTime=$reportTime)"
    }
}