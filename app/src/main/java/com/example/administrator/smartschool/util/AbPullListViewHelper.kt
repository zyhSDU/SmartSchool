package com.example.administrator.smartschool.util

import android.content.Context
import com.ab.view.listener.AbOnListViewListener
import com.ab.view.pullview.AbPullListView
import com.example.administrator.smartschool.R

/**
 * Created by Administrator on 2018/6/9 0009.
 */

/**
 * 0.setEnable
 * 1.setProgressBarDrawable//非必要
 * 2.setAbOnListViewListener
 */

object AbPullListViewHelper {
    fun setEnable(
            abPullListView: AbPullListView,
            enablePullRefresh: Boolean = true,
            enablePullLoad: Boolean = true
    ) {
        abPullListView.setPullRefreshEnable(enablePullRefresh)
        abPullListView.setPullLoadEnable(enablePullLoad)
    }

    fun setHeaderProgressBarDrawable(
            abPullListView: AbPullListView,
            context: Context,
            resId: Int = R.drawable.progress_circular
    ) {
        abPullListView.headerView.setHeaderProgressBarDrawable(context.resources.getDrawable(resId))
    }

    fun setFooterProgressBarDrawable(
            abPullListView: AbPullListView,
            context: Context,
            resId: Int = R.drawable.progress_circular
    ) {
        abPullListView.footerView.setFooterProgressBarDrawable(context.resources.getDrawable(resId))
    }

    fun setProgressBarDrawable(
            abPullListView: AbPullListView,
            context: Context,
            headerResId: Int = R.drawable.progress_circular,
            footerResId: Int = R.drawable.progress_circular
    ) {
        setHeaderProgressBarDrawable(abPullListView, context, headerResId)
        setFooterProgressBarDrawable(abPullListView, context, footerResId)
    }

    fun setAbOnListViewListener(
            abPullListView: AbPullListView,
            onRefresh: () -> Unit = {},
            onLoadMore: () -> Unit = {}) {
        abPullListView.setAbOnListViewListener(object : AbOnListViewListener {
            override fun onRefresh() {
                onRefresh()
            }

            override fun onLoadMore() {
                onLoadMore()
            }
        })

    }
}
