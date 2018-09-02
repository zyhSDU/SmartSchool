package com.example.administrator.smartschool.ui.ac.abac.tab

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.AdapterView
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.adapter.rv.BusRVAdapter
import com.example.administrator.smartschool.bean.BusBean
import com.example.administrator.smartschool.bean.Campus
import com.example.administrator.smartschool.bean.CampusBean
import com.example.administrator.smartschool.ui.ac.abac.BaseAbAc
import com.example.administrator.smartschool.util.CallUtil
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
        startThread {
            CallUtil(allCampusHandler).allCampus()
        }
    }

    private val allCampusHandler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            val campusBean = msg.obj as CampusBean

            showToast("" + campusBean.code + "==" + campusBean.message)

            when (campusBean.code) {
                0 -> {
                    campusList = campusBean.`object`!!
                    val campusNameList = campusList.map { it.name!! }

                    SpinnerHelper.setSpinner(spinner_from_campus, this@BusAbAc, campusNameList)
                    SpinnerHelper.setSpinner(spinner_to_campus, this@BusAbAc, campusNameList)

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
        }
    }

    private fun busList() {
        startThread {
            CallUtil(busListHandler).busList()
        }
    }

    private val busListHandler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            val busBean = msg.obj as BusBean

            showToast("" + busBean.code + "==" + busBean.message)

            when (busBean.code) {
                0 -> {
                    RecyclerViewHelper.initVerticalRecyclerView(
                            rv_bus,
                            this@BusAbAc,
                            BusRVAdapter(this@BusAbAc, busBean.`object`!!)
                    )
                }
            }
        }
    }

    private fun busListByPath(from: Int, to: Int) {
        startThread {
            CallUtil(busListByPathHandler).busListByPath(from, to)
        }
    }

    private val busListByPathHandler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            val busBean = msg.obj as BusBean

            showToast("" + busBean.code + "==" + busBean.message)

            when (busBean.code) {
                0 -> {
                    RecyclerViewHelper.initVerticalRecyclerView(
                            rv_bus,
                            this@BusAbAc,
                            BusRVAdapter(this@BusAbAc, busBean.`object`!!)
                    )
                }
            }
        }
    }
}
