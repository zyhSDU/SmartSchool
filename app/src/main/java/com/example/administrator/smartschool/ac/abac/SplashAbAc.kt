package com.example.administrator.smartschool.ac.abac

import android.content.Intent
import android.os.Handler
import android.view.MotionEvent
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.ac.ac.MainActivity

class SplashAbAc : BaseAbAc() {
    private val handler = Handler()

    override val layoutResId: Int
        get() = R.layout.ac_splash

    override fun initOnCreate() {
        delayEnterHome()
    }

    private fun delayEnterHome() {
        handler.postDelayed({
            enterHome()
        }, 3000)
    }

    private fun enterHome() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                handler.removeCallbacksAndMessages(null)
                enterHome()
            }
        }
        return super.onTouchEvent(event)
    }
}
