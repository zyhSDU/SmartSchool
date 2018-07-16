package com.example.administrator.smartschool.util

import android.content.Context
import com.ab.view.listener.AbOnListViewListener
import com.ab.view.pullview.AbPullGridView
import com.example.administrator.smartschool.R

/**
 * 0.setEnable
 * 1.setProgressBarDrawable//非必要
 * 2.setAbOnListViewListener
 */

object AbPullGridViewHelper {
    fun setEnable(
            abPullGridView: AbPullGridView,
            enablePullRefresh: Boolean = true,
            enablePullLoad: Boolean = true
    ) {
        abPullGridView.setPullRefreshEnable(enablePullRefresh)
        abPullGridView.setPullLoadEnable(enablePullLoad)
    }

    fun setHeaderProgressBarDrawable(
            abPullGridView: AbPullGridView,
            context: Context,
            resId: Int = R.drawable.progress_circular
    ) {
        abPullGridView.headerView.setHeaderProgressBarDrawable(context.resources.getDrawable(resId))
    }

    fun setFooterProgressBarDrawable(
            abPullGridView: AbPullGridView,
            context: Context,
            resId: Int = R.drawable.progress_circular
    ) {
        abPullGridView.footerView.setFooterProgressBarDrawable(context.resources.getDrawable(resId))
    }

    fun setProgressBarDrawable(
            abPullGridView: AbPullGridView,
            context: Context,
            headerResId: Int = R.drawable.progress_circular,
            footerResId: Int = R.drawable.progress_circular
    ) {
        setHeaderProgressBarDrawable(abPullGridView, context, headerResId)
        setFooterProgressBarDrawable(abPullGridView, context, footerResId)
    }

    fun setAbOnListViewListener(
            abPullGridView: AbPullGridView,
            onRefresh: () -> Unit = {},
            onLoadMore: () -> Unit = {}) {
        abPullGridView.setAbOnListViewListener(object : AbOnListViewListener {
            override fun onRefresh() {
                onRefresh()
            }

            override fun onLoadMore() {
                onLoadMore()
            }
        })

    }
}