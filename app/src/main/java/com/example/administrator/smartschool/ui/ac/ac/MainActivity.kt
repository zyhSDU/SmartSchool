package com.example.administrator.smartschool.ui.ac.ac

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.adapter.BaseFragmentPagerAdapter
import com.example.administrator.smartschool.constants.Setting
import com.example.administrator.smartschool.ui.fragment.v4.BottomFragment1
import com.example.administrator.smartschool.ui.fragment.v4.BottomFragment2
import com.example.administrator.smartschool.ui.fragment.v4.BottomFragment3
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        setBottomNavigationView()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

    }

    private fun setBottomNavigationView() {
        viewPager_main.adapter = object : BaseFragmentPagerAdapter(supportFragmentManager) {
            init {
                fragmentList = arrayListOf(
                        BottomFragment1(),
                        BottomFragment2(),
                        BottomFragment3()
                )
            }
        }
        viewPager_main.setOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                bottomNavigationView_main.menu.getItem(position).isChecked = true
            }
        })
        bottomNavigationView_main.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_0_menu_bottom_main -> viewPager_main.currentItem = 0
                R.id.item_1_menu_bottom_main -> viewPager_main.currentItem = 1
                R.id.item_2_menu_bottom_main -> viewPager_main.currentItem = 2
            }
            true
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_camera -> {
            }
            R.id.nav_gallery -> {
            }
            R.id.nav_slideshow -> {
            }
            R.id.nav_manage -> {
            }
            R.id.nav_share -> {
            }
            R.id.nav_send -> {
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    companion object {
        fun start(context: Context, identity:Int =0) {
            val starter = Intent(context, MainActivity::class.java)
            Setting.identity = identity
            context.startActivity(starter)
        }
    }
}
