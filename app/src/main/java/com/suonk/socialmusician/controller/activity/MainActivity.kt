package com.suonk.socialmusician.controller.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle

import com.google.android.material.navigation.NavigationView
import com.suonk.socialmusician.R

import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import android.view.MenuItem
import android.widget.*
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.suonk.socialmusician.controller.CircularImageView
import com.suonk.socialmusician.controller.MusiciansGridViewAdapter

class MainActivity : AppCompatActivity() {

    //region ========================================== Var or Val ==========================================

    private var main_DrawerLayout: DrawerLayout? = null
    private var main_NavigationView: NavigationView? = null

    private var main_GridView: GridView? = null
    private var main_Listview: ListView? = null

    private var mAuth: FirebaseAuth? = null

    //Nav_Header_View
    private var nav_Header: LinearLayout? = null
    private var nav_HeaderUserImage: CircularImageView? = null
    private var nav_HeaderUserName: TextView? = null
    private var nav_HeaderUserInstrument: TextView? = null
    private var nav_HeaderUserMusicStyle: TextView? = null

    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedIsNotConnected = getSharedPreferences("isNotConnected", Context.MODE_PRIVATE)
        if (sharedIsNotConnected.getBoolean("isNotConnected", true)) {
            startActivity(Intent(this@MainActivity, SignInActivity::class.java))
            finish()
        }

        //region ====================================== FindViewById() ======================================

        main_GridView = findViewById(R.id.main_grid_view_id)

        //endregion

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance()

        //region ========================================== Toolbar =========================================

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar.setHomeAsUpIndicator(R.drawable.ic_open_drawer)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))

        //endregion

        //region ======================================= DrawerLayout =======================================

        // Drawerlayout
        main_DrawerLayout = findViewById(R.id.main_drawer_layout)
        main_NavigationView = findViewById(R.id.main_nav_view)
        val menuItem = main_NavigationView!!.menu

        //On récupère l'item correspondant à l'activité Home-Contacts
        val navItem = menuItem.findItem(R.id.nav_home)

        //Puis nous la mettons en surbrillance par rapport aux autres options
        navItem.isChecked = true

        //Lorsque l'utilisateur clique sur un des éléments du drawer nous le fermons puis ouvrons une nouvelle activité
        main_NavigationView!!.setNavigationItemSelectedListener {
            //   menuItem.isChecked = true
            main_DrawerLayout!!.closeDrawers()
            when (it.itemId) {
                R.id.nav_home -> {
                }
                R.id.nav_friends -> {
//                startActivity(Intent(this@MainActivity, MyFriendsActivity::class.java))
                }
                R.id.nav_settings -> {

                }
                R.id.nav_help -> {

                }
                R.id.nav_log_out -> {
                    val sharedDisconnected = getSharedPreferences("isNotConnected", Context.MODE_PRIVATE)
                    val edit = sharedDisconnected.edit()
                    edit.putBoolean("isNotConnected", true)
                    edit.apply()
                    startActivity(Intent(this@MainActivity, SignInActivity::class.java))
                    finish()
                }
            }

            true
        }

        //endregion

        //region ================================ Intent ==========================================

        val user_id = intent.getIntExtra("user_id", 1)


        Toast.makeText(this@MainActivity, mAuth!!.currentUser!!.email.toString(),
                Toast.LENGTH_SHORT).show()

        //endregion

//        val gridViewAdapter = MusiciansGridViewAdapter(this, listOfMusicians)
//        main_GridView!!.adapter = gridViewAdapter
    }

    //region =========================================== Functions ==========================================

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                main_DrawerLayout!!.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    //endregion
}
