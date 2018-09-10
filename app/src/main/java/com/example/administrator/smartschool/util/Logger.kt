package com.example.administrator.smartschool.util

import android.util.Log

object Logger {
    private var isShowLog = true

    fun i(objTag: Any, msg: String?) {
        if (!isShowLog) {
            return
        }
        val tag: String = when (objTag) {
            is String -> objTag
            is Class<*> -> objTag.simpleName
            else -> objTag.javaClass.simpleName
        }
        Log.i(tag,""+ msg)
    }

    fun e(objTag: Any, msg: String?) {
        if (!isShowLog) {
            return
        }
        val tag: String = when (objTag) {
            is String -> objTag
            is Class<*> -> objTag.simpleName
            else -> objTag.javaClass.simpleName
        }
        Log.e(tag,""+ msg)
    }
}
