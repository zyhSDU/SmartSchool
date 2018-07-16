package com.example.administrator.smartschool.util

import android.content.Context
import com.ab.task.AbTaskItem
import com.ab.view.pullview.AbPullView
import com.example.administrator.smartschool.R

/**
 * Created by Administrator on 2018/6/8 0008.
 */

object AbPullViewHelper {
    fun setHeaderProgressBarDrawable(
            abPullView: AbPullView,
            context: Context,
            resId: Int = R.drawable.progress_circular
    ) {
        abPullView.headerView.setHeaderProgressBarDrawable(context.resources.getDrawable(resId))
    }

    /**
     * 给
     * @param abPullView
     * 添加下拉时的执行事件
     * @param abTaskItem
     */
    fun setAbOnRefreshListener(abPullView: AbPullView, abTaskItem: AbTaskItem) {
        abPullView.setAbOnRefreshListener({
            AbTaskItemHelper.execute(abTaskItem)
        })
    }
}
