package com.example.administrator.smartschool.ui.ac.ac

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.administrator.smartschool.R
import com.example.administrator.smartschool.adapter.BaseFragmentPagerAdapter
import com.example.administrator.smartschool.ui.fragment.v4.BottomFragment1
import com.example.administrator.smartschool.ui.fragment.v4.BottomFragment2
import com.example.administrator.smartschool.ui.fragment.v4.BottomFragment3
import com.example.administrator.smartschool.util.BroadcastHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var isAdministrator = false
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

        val stringExtra = intent.getStringExtra(string_isAdministrator)
        isAdministrator = stringExtra == "true"
        registerIsBottomFragment1RecyclerView1AdapterOkReceiver()
    }

    private fun registerIsBottomFragment1RecyclerView1AdapterOkReceiver() {
        val broadcastHelper = BroadcastHelper()
        broadcastHelper.registerReceiver(
                this@MainActivity,
                string_isBottomFragment1RecyclerView1AdapterOk,
                { context, intent ->
                    if (isAdministrator) {
                        sendBroadcast(Intent(MainActivity.string_updateAdministratorUIReceiver))
                    }
                    broadcastHelper.unregisterReceiver()
                }
        )
    }

    private fun setBottomNavigationView() {
        ViewPager_main.adapter = object : BaseFragmentPagerAdapter(supportFragmentManager) {
            init {
                fragmentList = arrayListOf(
                        BottomFragment1(),
                        BottomFragment2(),
                        BottomFragment3()
                )
            }
        }
        bottomNavigationView_main.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_0_menu_bottom_main -> ViewPager_main.currentItem = 0
                R.id.item_1_menu_bottom_main -> ViewPager_main.currentItem = 1
                R.id.item_2_menu_bottom_main -> ViewPager_main.currentItem = 2
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
        val string_isAdministrator = "isAdministrator"
        val string_updateAdministratorUIReceiver = "updateAdministratorUIReceiver"
        val string_isBottomFragment1RecyclerView1AdapterOk = "isBottomFragment1RecyclerView1AdapterOk"
        fun start(context: Context, string: String) {
            val starter = Intent(context, MainActivity::class.java)
            starter.putExtra(string_isAdministrator, string)
            context.startActivity(starter)
        }
    }
}
