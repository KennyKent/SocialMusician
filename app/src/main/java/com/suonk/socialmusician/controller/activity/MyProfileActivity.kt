package com.suonk.socialmusician.controller.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.suonk.socialmusician.R

class MyProfileActivity : AppCompatActivity(){

    //region ========================================== Var or Val ==========================================

    private var drawerLayout: DrawerLayout? = null

    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        //region ========================================== Toolbar =========================================

//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)
//        val actionbar = supportActionBar
//        actionbar!!.setDisplayHomeAsUpEnabled(true)
//        actionbar.setHomeAsUpIndicator(R.drawable.ic_open_drawer)
//        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))

        //endregion

        //region ======================================= FindViewById =======================================

        drawerLayout = findViewById(R.id.drawer_layout)

        //endregion
    }

//    //region =========================================== Functions ==========================================
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            android.R.id.home -> {
//                drawerLayout!!.openDrawer(GravityCompat.START)
//                return true
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
//
//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        // Handle navigation view item clicks here.
//
//        when (item.itemId) {
//            R.id.nav_home -> {
//                startActivity(Intent(this@MyProfileActivity, MainActivity::class.java))
//            }
//            R.id.nav_friends -> {
//                startActivity(Intent(this@MyProfileActivity, MyFriendsActivity::class.java))
//            }
//            R.id.nav_settings -> {
//
//            }
//            R.id.nav_help -> {
//
//            }
//        }
//
//        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
//        drawer.closeDrawer(GravityCompat.START)
//        return true
//    }
//
//    //endregion
}
