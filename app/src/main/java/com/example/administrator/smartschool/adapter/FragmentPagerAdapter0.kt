package com.example.administrator.smartschool.adapter

import android.support.v4.app.FragmentManager
import com.example.administrator.smartschool.fragment_v4.BottomFragment1
import com.example.administrator.smartschool.fragment_v4.BottomFragment2
import com.example.administrator.smartschool.fragment_v4.BottomFragment3

/**
 * Created by zhangYuHan on 2017/8/17.
 */

class FragmentPagerAdapter0(fm: FragmentManager) :BaseFragmentPagerAdapter(fm) {
    init {
        fragmentList= arrayListOf(BottomFragment1(), BottomFragment2(), BottomFragment3())
    }
}

