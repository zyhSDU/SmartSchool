package com.example.administrator.smartschool.ui.ac.abac.tab

import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.adapter.rv.TogetherInfoRVAdapter
import com.example.administrator.smartschool.bean.BaseBean
import com.example.administrator.smartschool.bean.TogetherListBean
import com.example.administrator.smartschool.net.CallUtil
import com.example.administrator.smartschool.ui.ac.abac.BaseAbAc
import com.example.administrator.smartschool.util.DialogHelper
import com.example.administrator.smartschool.util.RecyclerViewHelper
import com.example.administrator.smartschool.view.QuantityChooseView
import kotlinx.android.synthetic.main.activity_together_list.*


class TogetherListActivity : BaseAbAc() {
    override val layoutResId: Int
        get() = R.layout.activity_together_list

    override fun initOnCreate() {
        bottomNavigationView_together.menu.getItem(0).isEnabled = false
        bottomNavigationView_together.menu.getItem(1).isEnabled = false
        together_list(1, -1)
        btn_to_ac_add_together.setOnClickListener {
            showDialog()
        }
        bottomNavigationView_together.setOnNavigationItemSelectedListener {
            bottomNavigationView_together.menu.getItem(0).isEnabled = false
            bottomNavigationView_together.menu.getItem(1).isEnabled = false
            when (it.itemId) {
                R.id.item_0_menu_bottom_together -> together_list(1, -1)
                R.id.item_1_menu_bottom_together -> together_getSelfTog(1)
            }
            true
        }

    }
    private fun together_getSelfTog(page: Int){
        CallUtil{
            val togetherListBean = it.obj as TogetherListBean
            when(togetherListBean.code){
                0 -> {
                    RecyclerViewHelper.initVerticalRecyclerView(
                            rv_ac_together_list,
                            this,
                            TogetherInfoRVAdapter(
                                    this,
                                    togetherListBean.`object`!!
                            )
                    )
                }
            }
            bottomNavigationView_together.menu.getItem(0).isEnabled = true
        }.together_getSelfTog(page)
    }

    private fun showDialog() {
        DialogHelper.build(
                "发起约车约自习",
                this,
                R.layout.dialog_start_together,
                init = { dialogView ->
                    var type = 0
                    val quantityChooseView = dialogView.findViewById<QuantityChooseView>(R.id.quantityChooseView_dialog_start_together)
                    val radioGroup = dialogView.findViewById<RadioGroup>(R.id.rg_dialog_start_together)
                    val editText = dialogView.findViewById<EditText>(R.id.et_dialog_start_together)
                    radioGroup.setOnCheckedChangeListener { group, checkedId ->
                        val rb = group.findViewById<RadioButton>(checkedId)
                        type = when {
                            rb.text.toString() == "约车" -> 0
                            else -> 1
                        }
                    }
                    return@build { dialog, which ->
                        together_start(
                                quantityChooseView.quantity,
                                type,
                                editText.text.toString()
                        )
                    }
                }
        ).show()
    }

    private fun together_start(num: Int, type: Int, zdescribe: String) {
        CallUtil {
            val baseBean = it.obj as BaseBean
            showToast(baseBean.message)
        }.together_start(num, type, zdescribe)
    }

    private fun together_list(page: Int, type: Int) {
        CallUtil {
            val togetherListBean = it.obj as TogetherListBean
            when (togetherListBean.code) {
                0 -> {
                    RecyclerViewHelper.initVerticalRecyclerView(
                            rv_ac_together_list,
                            this,
                            TogetherInfoRVAdapter(
                                    this,
                                    togetherListBean.`object`!!
                            )
                    )
                }
            }
            bottomNavigationView_together.menu.getItem(1).isEnabled = true
        }.together_list(page, type)
    }
}
