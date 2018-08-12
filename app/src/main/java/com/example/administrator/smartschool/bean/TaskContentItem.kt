package com.example.administrator.smartschool.bean

import java.io.Serializable
import java.util.*

/**
 * Created by Administrator on 2018/2/2 0002.
 */

class TaskContentItem(
        var id: Int = 0,
        var name: String? = "",
        var province: String? = "",
        var university: String? = "",
        var time: Date? = null,
        var status: Int = 0
) : Serializable {
    override fun toString(): String {
        return "TaskContentItem(id=$id, name=$name, province=$province, university=$university, time=$time, status=$status)"
    }
}
