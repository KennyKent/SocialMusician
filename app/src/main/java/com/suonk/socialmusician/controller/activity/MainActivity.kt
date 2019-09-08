package com.suonk.socialmusician.controller.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.suonk.socialmusician.R
import com.suonk.socialmusician.controller.CircularImageView

class MainActivity : AppCompatActivity() {

    //region ========================================== Var or Val ==========================================

    private var main_DrawerLayout: DrawerLayout? = null
    private var main_NavigationView: NavigationView? = null

    private var main_GridView: GridView? = null
    private var main_Listview: ListView? = null

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

        //endregion

        // Initialize Firebase Auth
        val mAuth = FirebaseAuth.getInstance()

        //region ========================================== Toolbar =========================================

        val main_ToolbarLayout = findViewById<RelativeLayout>(R.id.main_toolbar_layout)
        val main_ToolbarSearch = findViewById<AppCompatEditText>(R.id.main_toolbar_search)
        val main_ToolbarOpenChat = findViewById<AppCompatImageView>(R.id.main_toolbar_messaging)
        val main_ToolbarOpenDrawer = findViewById<AppCompatImageView>(R.id.main_toolbar_open_drawer)

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

        //region ========================================== Intent ==========================================

        val user_id = intent.getIntExtra("user_id", 1)

        val user = mAuth.currentUser

        //endregion

        //region ======================================== Listeners =========================================

        main_ToolbarOpenChat.setOnClickListener {

        }

        main_ToolbarOpenDrawer.setOnClickListener {
            main_DrawerLayout!!.openDrawer(GravityCompat.START)
        }

        //endregion
    }

    //region =========================================== Functions ==========================================

    override fun onBackPressed() {
        if (main_DrawerLayout!!.isDrawerOpen(GravityCompat.START)) {
            main_DrawerLayout!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    //endregion
}
