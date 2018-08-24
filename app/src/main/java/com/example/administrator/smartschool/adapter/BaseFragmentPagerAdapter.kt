package com.example.administrator.smartschool.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.administrator.smartschool.fragment_v4.BaseFragment

/**
 * Created by zhangYuHan on 2017/8/17.
 */

/**
 * 使用例子
//        ViewPager_main.adapter = object : BaseFragmentPagerAdapter(supportFragmentManager){
//            init {
//                fragmentList= arrayListOf(BottomFragment1(), BottomFragment2(), BottomFragment3())
//            }
//        }
 */
abstract class BaseFragmentPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    protected lateinit var fragmentList: ArrayList<BaseFragment>

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }
}

