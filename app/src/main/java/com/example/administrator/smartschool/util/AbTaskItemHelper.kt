package com.example.administrator.smartschool.util

import com.ab.task.*

/**
 * Created by Administrator on 2018/6/2 0002.
 */

/*  0.载体
        00.AbThread()	    //Handler Message线程执行单个任务
        01.AbTask()		    //异步线程执行单个任务
        02.AbTaskQueue()	//任务队列	然后提交给线程池
            020.queue.quit()	            //强制停止
            021.queue.execute(item_1, true)	//强制停止前面的请求
        03.AbTaskPool.getInstance()	//线程池
    1.AbTaskItem()
        10.AbTaskListener()
            100.update()	//任务完成，一般用来更新UI
            101.get()		//任务执行，一般用来进行耗时操作
    2.载体.execute(item)
 */

object AbTaskItemHelper {
    private val abTaskPool = AbTaskPool.getInstance()

    fun getAbTaskItem(update: () -> Unit = {}, get: () -> Unit = {}): AbTaskItem {
        val abTaskItem = AbTaskItem()
        abTaskItem.listener = object : AbTaskListener() {
            override fun update() {
                update()
            }

            override fun get() {
                get()
            }
        }
        return abTaskItem
    }

    fun execute(abTaskItem: AbTaskItem) {
        abTaskPool.execute(abTaskItem)
    }

    fun execute(update: () -> Unit = {}, get: () -> Unit = {}) {
        execute(getAbTaskItem(update, get))
    }

    fun executeNullAbTaskItemLast(millis: Long = 1000, update: () -> Unit = {}) {
        execute(update, get = {
            Thread.sleep(millis)
        })
    }
}
