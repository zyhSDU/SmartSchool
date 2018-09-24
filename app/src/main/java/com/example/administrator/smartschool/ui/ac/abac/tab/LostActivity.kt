package com.example.administrator.smartschool.ui.ac.abac.tab

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.bean.BaseBeanWithObject
import com.example.administrator.smartschool.net.CallUtil
import com.example.administrator.smartschool.ui.ac.abac.BaseAbAc
import com.example.administrator.smartschool.util.Base64Util
import com.example.administrator.smartschool.util.Logger
import kotlinx.android.synthetic.main.activity_lost.*
import java.io.FileNotFoundException

class LostActivity : BaseAbAc() {
    override val layoutResId: Int
        get() = R.layout.activity_lost

    override fun initOnCreate() {
        mAbTitleBar.setTitleText("上报丢失")
        iv_ac_lost.setOnClickListener {
            openAlbum()
        }
        tv_ac_lost.setOnClickListener {
            iv_ac_lost.performClick()
        }
        btn_ac_lost.setOnClickListener {
            iv_ac_lost.isDrawingCacheEnabled = true
            val bitmap = Bitmap.createBitmap(iv_ac_lost.drawingCache)
            iv_ac_lost.isDrawingCacheEnabled = false
            val bitmapToBase64 = Base64Util.bitmapToBase64(bitmap)
            Logger.e("smartCampus",bitmapToBase64)
            lost_submitLost(
                    et_zdescribe_ac_lost.text.toString(),
                    et_phone_ac_lost.text.toString(),
                    bitmapToBase64
            )
        }
    }

    private fun lost_submitLost(zdescribe: String, phone: String, picCode: String?) {
        CallUtil {
            val baseBeanWithObject = it.obj as BaseBeanWithObject
            showToast(baseBeanWithObject.message)
        }.lost_submitLost(zdescribe, phone, picCode)
    }

    /**
     * 打开系统相册
     */
    private fun openAlbum() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        //设置请求码，以便我们区分返回的数据
        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (100 == requestCode) {
            if (data != null) {
                //获取数据
                //获取内容解析者对象
                try {
                    val mBitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(data.data))
                    iv_ac_lost.setImageBitmap(mBitmap)
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                }
            }
        }
    }
}
