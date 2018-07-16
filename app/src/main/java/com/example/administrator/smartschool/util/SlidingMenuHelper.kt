package com.example.administrator.smartschool.util

import android.app.Activity
import com.ab.view.slidingmenu.SlidingMenu
import com.example.administrator.smartschool.R

/**
 * Created by Administrator on 2018/6/10 0010.
 */

object SlidingMenuHelper{
    fun test(activity: Activity): SlidingMenu {
        val menu = SlidingMenu(activity)
        menu.mode = SlidingMenu.LEFT
        // 设置触摸屏幕的模式
        menu.touchModeAbove = SlidingMenu.TOUCHMODE_FULLSCREEN
        menu.setShadowWidthRes(R.dimen.shadow_width)
        menu.setShadowDrawable(R.drawable.shadow)

        // 设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset)
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f)
        /**
         * SLIDING_WINDOW will include the Title/ActionBar in the content
         * section of the SlidingMenu, while SLIDING_CONTENT does not.
         */
        //把滑动菜单添加进所有的Activity中，可选值SLIDING_CONTENT ， SLIDING_WINDOW
        menu.attachToActivity(activity, SlidingMenu.SLIDING_CONTENT)
        //为侧滑菜单设置布局
        menu.setMenu(R.layout.leftmenu)
        return menu
    }
}
