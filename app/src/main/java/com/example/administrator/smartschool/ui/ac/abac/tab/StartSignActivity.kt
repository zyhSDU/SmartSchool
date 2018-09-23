package com.example.administrator.smartschool.ui.ac.abac.tab

import android.graphics.Bitmap
import android.view.View
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.adapter.rv.SignerListRVAdapter
import com.example.administrator.smartschool.bean.BaseBean
import com.example.administrator.smartschool.bean.BaseBeanWithObject
import com.example.administrator.smartschool.bean.Sign
import com.example.administrator.smartschool.bean.SignerListBean
import com.example.administrator.smartschool.net.CallUtil
import com.example.administrator.smartschool.ui.ac.abac.BaseAbAc
import com.example.administrator.smartschool.util.Base64Util
import com.example.administrator.smartschool.util.RecyclerViewHelper
import com.google.zxing.*
import com.google.zxing.common.HybridBinarizer
import com.google.zxing.decoding.RGBLuminanceSource
import com.google.zxing.qrcode.QRCodeReader
import kotlinx.android.synthetic.main.activity_start_sign.*
import java.util.*

class StartSignActivity : BaseAbAc() {
    override val layoutResId: Int
        get() = R.layout.activity_start_sign

    override fun initOnCreate() {
        iv_erWeiMa_startSign.isClickable = false

        btn_startSign.setOnClickListener {
            CallUtil {
                val baseBeanWithObject = it.obj as BaseBeanWithObject
                if (baseBeanWithObject.code == 0) {
                    var s = baseBeanWithObject.`object` as String
                    s = s.substring("data:image/png;base64,".length)
                    val base64ToBitmap = Base64Util.base64ToBitmap(s)
                    iv_erWeiMa_startSign.setImageBitmap(base64ToBitmap)
                    iv_erWeiMa_startSign.isClickable = true
                    btn_stopSign.visibility=View.VISIBLE
                    btn_getSignList.visibility=View.VISIBLE
                }
            }.sign_startSign(et_pwd_startSign.text.toString())
        }
        btn_stopSign.setOnClickListener {
            if (sign == null) {
                runOnUiThread {
                    iv_erWeiMa_startSign.isDrawingCacheEnabled = true
                    val bitmap = Bitmap.createBitmap(iv_erWeiMa_startSign.drawingCache)
                    iv_erWeiMa_startSign.isDrawingCacheEnabled = false
                    val result = scanningImage(bitmap)
                    if (result != null) {
                        val string = result.text
                        sign = CallUtil.gson.fromJson(string, Sign::class.java)
                        together_stop(sign!!.signId!!.toInt())
                    } else {
                        showToast("识别失败")
                    }
                }
            } else {
                together_stop(sign!!.signId!!.toInt())
            }
        }
        btn_getSignList.setOnClickListener {
            if (sign == null) {
                runOnUiThread {
                    iv_erWeiMa_startSign.isDrawingCacheEnabled = true
                    val bitmap = Bitmap.createBitmap(iv_erWeiMa_startSign.drawingCache)
                    iv_erWeiMa_startSign.isDrawingCacheEnabled = false
                    val result = scanningImage(bitmap)
                    if (result != null) {
                        val string = result.text
                        sign = CallUtil.gson.fromJson(string, Sign::class.java)
                        sign_getSignList(sign!!.signId!!.toInt(), 1)
                    } else {
                        showToast("识别失败")
                    }
                }
            } else {
                sign_getSignList(sign!!.signId!!.toInt(), 1)
            }
        }
    }

    private fun together_stop(signId: Int) {
        CallUtil {
            val baseBean = it.obj as BaseBean
            showToast(baseBean.message)
        }.sign_stopSign(signId)
    }

    private fun sign_getSignList(signId: Int, page: Int) {
        CallUtil {
            val baseBean = it.obj as SignerListBean
            if (baseBean.code == 0) {
                RecyclerViewHelper.initVerticalRecyclerView(
                        rv_signList,
                        this@StartSignActivity,
                        SignerListRVAdapter(
                                this@StartSignActivity,
                                baseBean.`object`!!
                        )
                )
            }
        }.sign_getSignList(signId, page)
    }

    var sign: Sign? = null
    /**
     * 扫描二维码图片的方法
     * @param path
     * @return
     */
    fun scanningImage(bitmap: Bitmap): Result? {
        val hints = Hashtable<DecodeHintType, String>()
        hints[DecodeHintType.CHARACTER_SET] = "UTF8" //设置二维码内容的编码
        val source = RGBLuminanceSource(bitmap)
        val bitmap1 = BinaryBitmap(HybridBinarizer(source))
        val reader = QRCodeReader()
        try {
            return reader.decode(bitmap1, hints)
        } catch (e: NotFoundException) {
            e.printStackTrace()
        } catch (e: ChecksumException) {
            e.printStackTrace()
        } catch (e: FormatException) {
            e.printStackTrace()
        }

        return null
    }
}
