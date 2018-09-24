package com.example.administrator.smartschool.ui.ac.abac.tab

import android.content.Intent
import com.dommy.qrcode.util.Constant
import com.dommy.qrcode.util.Constant.INTENT_EXTRA_KEY_QR_SCAN
import com.dommy.qrcode.util.Constant.REQ_QR_CODE
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.bean.BaseBean
import com.example.administrator.smartschool.bean.Sign
import com.example.administrator.smartschool.net.CallUtil
import com.example.administrator.smartschool.ui.ac.abac.BaseAbAc
import com.example.administrator.smartschool.util.Utils.dynamicPermission
import com.google.zxing.activity.CaptureActivity
import kotlinx.android.synthetic.main.activity_sign.*

class SignActivity : BaseAbAc() {
    override val layoutResId: Int
        get() = R.layout.activity_sign

    override fun initOnCreate() {
        dynamicPermission(this, android.Manifest.permission.CAMERA)
        btn_partOne_ac_sign.setOnClickListener {
            val intent = Intent(this, CaptureActivity::class.java)
            startActivityForResult(intent, Constant.REQ_QR_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_QR_CODE && resultCode == -1) {
            val bundle = data.extras
            val string = bundle.getString(INTENT_EXTRA_KEY_QR_SCAN)
            val sign = CallUtil.gson.fromJson(string, Sign::class.java)
            CallUtil {
                val baseBean = it.obj as BaseBean
                val toast = when {
                    baseBean.code == 0 -> "签到成功"
                    else -> baseBean.message
                }
                tv_ac_sign.text = toast
            }.sign_partOne(sign.signId!!.toInt(), sign.password!!)
        }
    }
}
