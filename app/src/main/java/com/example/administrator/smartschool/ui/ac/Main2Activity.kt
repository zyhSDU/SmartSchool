package com.example.administrator.smartschool.ui.ac

import android.content.Context
import android.content.Intent
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.ImageView
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.adapter.BaseFragmentPagerAdapter
import com.example.administrator.smartschool.constants.Setting
import com.example.administrator.smartschool.ui.ac.abac.BaseAbAc
import com.example.administrator.smartschool.ui.fragment.v4.BottomFragment1
import com.example.administrator.smartschool.ui.fragment.v4.BottomFragment2
import com.example.administrator.smartschool.ui.fragment.v4.BottomFragment3
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : BaseAbAc() {
    override val layoutResId: Int
        get() = R.layout.activity_main2

    override fun initOnCreate() {
        mAbTitleBar.setTitleText("智汇校园")
        setBottomNavigationView()
    }

    private fun setBottomNavigationView() {
        viewPager_main2.adapter = object : BaseFragmentPagerAdapter(supportFragmentManager) {
            init {
                fragmentList = arrayListOf(
                        BottomFragment1(),
                        BottomFragment1(),
                        BottomFragment3()
                )
            }
        }
        viewPager_main2.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                bottomNavigationView_main2.menu.getItem(position).isChecked = true
            }
        })
        bottomNavigationView_main2.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_0_menu_bottom_main2 -> viewPager_main2.currentItem = 0
                R.id.item_1_menu_bottom_main2 -> viewPager_main2.currentItem = 1
                R.id.item_2_menu_bottom_main2 -> viewPager_main2.currentItem = 2
            }
            true
        }
    }

    companion object {
        fun start(context: Context, identity: Int = 0) {
            val starter = Intent(context, Main2Activity::class.java)
            Setting.identity = identity
            context.startActivity(starter)
        }
    }

}
