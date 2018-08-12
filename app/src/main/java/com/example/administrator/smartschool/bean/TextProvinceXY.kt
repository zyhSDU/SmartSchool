package com.example.administrator.smartschool.bean

/**
 * Created by Administrator on 2017/12/7 0007.
 */
class TextProvinceXY(
        var text: String = "",
        var province: String = "",
        var x: Float = 0f,
        var y: Float = 0f
){
    override fun toString(): String {
        return "TextProvinceXY(text='$text', province='$province', x=$x, y=$y)"
    }
}