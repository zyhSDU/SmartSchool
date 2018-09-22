package com.example.administrator.smartschool.ui.ac.abac.tab

import android.content.Context
import android.content.Intent
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.adapter.rv.TogetherParterRVAdapter
import com.example.administrator.smartschool.bean.TogetherParter
import com.example.administrator.smartschool.ui.ac.abac.BaseAbAc
import com.example.administrator.smartschool.util.RecyclerViewHelper
import kotlinx.android.synthetic.main.activity_together_parter_list.*

class TogetherParterListActivity : BaseAbAc() {
    override val layoutResId: Int
        get() = R.layout.activity_together_parter_list

    override fun initOnCreate() {
        val serializableExtra = intent.getSerializableExtra(string_list)
        val list = serializableExtra as ArrayList<TogetherParter>
        RecyclerViewHelper.initVerticalRecyclerView(
                rv_ac_together_parter_list,
                this,
                TogetherParterRVAdapter(this,list)
        )
    }

    companion object {
        val string_list="list"
        fun start(context: Context, list: ArrayList<TogetherParter>) {
            val starter = Intent(context, TogetherParterListActivity::class.java)
            starter.putParcelableArrayListExtra(string_list,list)
            context.startActivity(starter)
        }
    }
}
