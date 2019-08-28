package com.suonk.musiciansocialnetwork.controller.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle

import com.google.android.material.navigation.NavigationView
import com.suonk.musiciansocialnetwork.R

import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import android.view.MenuItem
import android.view.View
import android.widget.GridView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.suonk.musiciansocialnetwork.controller.CircularImageView
import com.suonk.musiciansocialnetwork.controller.MusiciansGridViewAdapter
import com.suonk.musiciansocialnetwork.model.MusicStyle
import com.suonk.musiciansocialnetwork.model.Musicians
import com.suonk.musiciansocialnetwork.model.User
import java.util.ArrayList

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    //region ========================================== Var or Val ==========================================

    private var drawerLayout: DrawerLayout? = null

    private var main_GridView: GridView? = null
    private var main_Listview: ListView? = null

    //Nav_Header_View
    private var nav_Header: LinearLayout? = null
    private var nav_HeaderUserImage: CircularImageView? = null
    private var nav_HeaderUserName: TextView? = null
    private var nav_HeaderUserInstrument: TextView? = null
    private var nav_HeaderUserMusicStyle: TextView? = null

    private val listOfMusicians: List<Musicians>
        get() {
            val listOfMusicians = ArrayList<Musicians>()
            val Leo = Musicians(1, "Leo", "Sevran, France", "Bla bla bla", 24, R.drawable.img_avatar, "Bass", MusicStyle(""), true)
            val Juju = Musicians(2, "Juju", "Sevran, France", "Bliblo", 26, R.drawable.img_avatar, "Bass", MusicStyle(""), true)
            val Thomas = Musicians(3, "Thomas", "Blancoc, France", "Ble ble", 22, R.drawable.img_avatar, "Drum", MusicStyle(""), true)
            val Matthias = Musicians(4, "Matthias", "Villejuif, France", "", 20, R.drawable.img_avatar, "Keyboard", MusicStyle(""), true)
            val Flo = Musicians(5, "Flo", "Aulnay Sous Bois, France", "bdjqodijzdoijqz", 24, R.drawable.img_avatar, "Guitar", MusicStyle(""), true)
            val Adri = Musicians(6, "Adri", "Ermont - Eaubonne, France", "bdjqodijzdoijqz", 20, R.drawable.img_avatar, "Sax", MusicStyle(""), true)
            val Samson = Musicians(7, "Samson", "Villiers sur Marne, France", "bdjqodijzdoijqz", 18, R.drawable.img_avatar, "Drum", MusicStyle(""), true)

            listOfMusicians.add(Leo)
            listOfMusicians.add(Juju)
            listOfMusicians.add(Thomas)
            listOfMusicians.add(Matthias)
            listOfMusicians.add(Flo)
            listOfMusicians.add(Adri)
            listOfMusicians.add(Samson)

            return listOfMusicians
        }

    private val listOfMusicStyle: List<MusicStyle>
        get() {
            val listOfMusicStyle = ArrayList<MusicStyle>()

            listOfMusicStyle.add(MusicStyle("Jazz Funk"))
            listOfMusicStyle.add(MusicStyle("Blues Soul"))

            return listOfMusicStyle
        }

    private val user: User
        get() = User("Kenny", "Paris, France",
                "Bla bla bla bla bla bla", "Guitariste",
                listOfMusicStyle, R.drawable.kenzy_profil_image, 22)

    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //region ====================================== FindViewById() ======================================

        main_GridView = findViewById(R.id.main_grid_view_id)

        //endregion

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
        drawerLayout = findViewById(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val headerView = navigationView.getHeaderView(0)
        nav_Header = headerView.findViewById(R.id.nav_header_main)
        nav_HeaderUserImage = headerView.findViewById(R.id.nav_header_my_profile_user_image)
        nav_HeaderUserName = headerView.findViewById(R.id.nav_header_my_profile_user_name)
        nav_HeaderUserInstrument = headerView.findViewById(R.id.nav_header_my_profile_music_instrument)
        nav_HeaderUserMusicStyle = headerView.findViewById(R.id.nav_header_my_profile_music_style)

        nav_HeaderUserImage!!.setImageResource(user.profileImage)
        nav_HeaderUserName!!.text = user.name
        nav_HeaderUserInstrument!!.text = user.instrument
        nav_HeaderUserMusicStyle!!.text = user.musicStyle[1].musicStyle

        nav_Header!!.setOnClickListener {
            startActivity(Intent(this@MainActivity, MyProfileActivity::class.java))
        }

        //endregion

        val gridViewAdapter = MusiciansGridViewAdapter(this, listOfMusicians)
        main_GridView!!.adapter = gridViewAdapter
    }

    //region =========================================== Functions ==========================================

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                drawerLayout!!.openDrawer(GravityCompat.START)
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.

        when (item.itemId) {
            R.id.nav_home -> {
            }
            R.id.nav_add_user -> {
                startActivity(Intent(this@MainActivity, MyFriendsActivity::class.java))
            }
            R.id.nav_settings -> {

            }
            R.id.nav_help -> {

            }
        }

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    //endregion
}
