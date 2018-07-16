package com.example.administrator.smartschool.ac

import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.MenuItem
import android.widget.LinearLayout
import com.ab.view.titlebar.AbTitleBar
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.adapter.BaseFragmentPagerAdapter
import com.example.administrator.smartschool.fragment.BottomFragment1
import com.example.administrator.smartschool.fragment.BottomFragment2
import com.example.administrator.smartschool.fragment.BottomFragment3
import com.example.administrator.smartschool.util.AbTitleBarHelper
import com.example.administrator.smartschool.util.Utils
import com.example.administrator.smartschool.util.P2DP
import kotlinx.android.synthetic.main.ac_main.*

/**
 * Created by Administrator on 2018/7/16 0016.
 */

class MainActivity : BaseAbAc(), BottomNavigationView.OnNavigationItemSelectedListener {
    override val layoutResId: Int
        get() = R.layout.ac_main

    override fun initOnCreate() {
        initAbTitleBar(mAbTitleBar)
        setBottomNavigationView()
        initPermission()
    }

    private fun initAbTitleBar(abTitleBar: AbTitleBar) {
        AbTitleBarHelper.initAbTitleBar(
                abTitleBar,
                resources.getString(R.string.app_name),
                R.color.black,
                R.drawable.haha)
        val layoutParams = abTitleBar.logoView.layoutParams as LinearLayout.LayoutParams
        layoutParams.setMargins(16, 0, 16, 0)
        val pixels = P2DP.pixels(this, 32)
        layoutParams.height = pixels
        layoutParams.width = pixels
    }

    private fun initPermission() {
        Utils.dynamicPermission(
                this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

    private fun setBottomNavigationView() {
        bottomNavigationView_main.setOnNavigationItemSelectedListener(this)

        val fragmentList = arrayListOf<Fragment>(BottomFragment1(), BottomFragment2(), BottomFragment3())
        val adapter = BaseFragmentPagerAdapter(supportFragmentManager, fragmentList)

        viewPager_main.adapter = adapter
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_0_menu_bottom_main -> {
                viewPager_main.currentItem = 0
                viewPager_main.currentItem = 0
               return true
            }
            R.id.item_1_menu_bottom_main ->{
                viewPager_main.currentItem = 1
                return true
            }
            R.id.item_2_menu_bottom_main -> {
                viewPager_main.currentItem = 2
                return true
            }
        }
        return false
    }
}