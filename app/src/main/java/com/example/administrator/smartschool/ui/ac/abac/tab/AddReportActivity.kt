package com.example.administrator.smartschool.ui.ac.abac.tab

import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.bean.BaseBeanWithObject
import com.example.administrator.smartschool.net.CallUtil
import com.example.administrator.smartschool.ui.ac.abac.BaseAbAc
import kotlinx.android.synthetic.main.activity_add_report.*

class AddReportActivity : BaseAbAc() {
    override val layoutResId: Int
        get() = R.layout.activity_add_report

    override fun initOnCreate() {
        mAbTitleBar.setTitleText("提交报修")
        btn_report.setOnClickListener {
            val string = et_report.text.toString()
            submitRepair(string)
        }
    }

    private fun submitRepair(describe: String) {
        if (describe.length > 80) {
            showToast("不得超过80个字")
            return
        }
        CallUtil({
            val baseBean = it.obj as BaseBeanWithObject
            showToast(baseBean.message)
        }).submitRepair(describe)
    }
}
