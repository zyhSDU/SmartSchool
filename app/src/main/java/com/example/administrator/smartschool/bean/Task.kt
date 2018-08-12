package com.example.administrator.smartschool.bean

import java.util.Date

class Task(
        var id: Int = 0,
        var name: String? = "",
        var sb: String? = "",
        var time: Date? = null,
        var status: Int = 0
)