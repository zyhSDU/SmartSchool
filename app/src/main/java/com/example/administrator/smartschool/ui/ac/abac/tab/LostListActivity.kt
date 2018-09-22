package com.example.administrator.smartschool.ui.ac.abac.tab

import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.adapter.rv.LostRVAdapter
import com.example.administrator.smartschool.bean.BaseBean
import com.example.administrator.smartschool.bean.LostListBean
import com.example.administrator.smartschool.net.CallUtil
import com.example.administrator.smartschool.ui.ac.abac.BaseAbAc
import com.example.administrator.smartschool.util.RecyclerViewHelper
import kotlinx.android.synthetic.main.activity_lost_list.*

class LostListActivity : BaseAbAc() {
    override val layoutResId: Int
        get() = R.layout.activity_lost_list

    override fun initOnCreate() {
        lost_getLostList(0)
    }

    private fun lost_getLostList(page: Int) {
        CallUtil {
            val lostListBean = it.obj as LostListBean
            when (lostListBean.code) {
                0 -> {
                    val list = lostListBean.`object`!!
                    RecyclerViewHelper.initVerticalRecyclerView(
                            rv_lost_list,
                            this,
                            LostRVAdapter(this, list)
                    )

                }
            }
        }.lost_getLostList(page)
    }
}
