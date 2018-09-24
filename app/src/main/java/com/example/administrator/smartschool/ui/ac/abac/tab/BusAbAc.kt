package com.example.administrator.smartschool.ui.ac.abac.tab

import android.view.View
import android.widget.AdapterView
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.adapter.rv.BusRVAdapter
import com.example.administrator.smartschool.bean.BusListBean
import com.example.administrator.smartschool.bean.Campus
import com.example.administrator.smartschool.bean.CampusListBean
import com.example.administrator.smartschool.ui.ac.abac.BaseAbAc
import com.example.administrator.smartschool.net.CallUtil
import com.example.administrator.smartschool.util.RecyclerViewHelper
import com.example.administrator.smartschool.util.SpinnerHelper
import kotlinx.android.synthetic.main.ac_bus.*

/**
 * Created by Administrator on 2018/7/18 0018.
 */

class BusAbAc : BaseAbAc() {
    override val layoutResId: Int
        get() = R.layout.ac_bus
    lateinit var campusList: List<Campus>
    override fun initOnCreate() {
        allCampus()
    }

    override fun initOnClick(view: View, id: Int) {
        when (id) {
            R.id.tv_from_campus -> {
                spinner_from_campus.performClick()
            }
            R.id.tv_to_campus -> {
                spinner_to_campus.performClick()
            }
            R.id.btn_search_all_bus_list -> {
                busList()
            }
            R.id.btn_search_bus_list -> {
                val from = campusList[spinner_from_campus.selectedItemId.toInt()].id
                val to = campusList[spinner_to_campus.selectedItemId.toInt()].id
                busListByPath(from, to)
            }
        }
    }

    private fun allCampus() {
        CallUtil({
            val campusBean = it.obj as CampusListBean
            when (campusBean.code) {
                0 -> {
                    campusList = campusBean.`object`!!
                    val campusNameList = campusList.map { it.name }

                    SpinnerHelper.setSpinner(
                            spinner_from_campus,
                            this@BusAbAc,
                            campusNameList,
                            R.layout.support_simple_spinner_dropdown_item
                    )
                    SpinnerHelper.setSpinner(
                            spinner_to_campus,
                            this@BusAbAc,
                            campusNameList,
                            R.layout.support_simple_spinner_dropdown_item
                    )

                    spinner_from_campus.onItemSelectedListener = object : SpinnerHelper.SpinnerOnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            tv_from_campus.text = campusList[position].name
                        }
                    }
                    spinner_to_campus.onItemSelectedListener = object : SpinnerHelper.SpinnerOnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            tv_to_campus.text = campusList[position].name
                        }
                    }
                }
            }
        }).allCampus()
    }

    private fun busList() {
        CallUtil({
            val busBean = it.obj as BusListBean
            when (busBean.code) {
                0 -> {
                    RecyclerViewHelper.initVerticalRecyclerView(
                            rv_bus,
                            this@BusAbAc,
                            BusRVAdapter(
                                    this@BusAbAc,
                                    busBean.`object`!!
                            )
                    )
                }
            }
        }).busList()
    }

    private fun busListByPath(from: Int, to: Int) {
        CallUtil({
            val busBean = it.obj as BusListBean
            when (busBean.code) {
                0 -> {
                    RecyclerViewHelper.initVerticalRecyclerView(
                            rv_bus,
                            this@BusAbAc,
                            BusRVAdapter(
                                    this@BusAbAc,
                                    busBean.`object`!!
                            )
                    )
                }
            }
        }).busListByPath(from, to)
    }
}
