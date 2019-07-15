package com.suonk.musiciansocialnetwork.controller.activity

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.suonk.musiciansocialnetwork.R

class SignUpActivity : AppCompatActivity() {

    //region ========================================== Var or Val ==========================================

    private var bottomNavigationView: BottomNavigationView? = null

    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT

        //region ========================================== FindViewById ===========================================

        bottomNavigationView = findViewById(R.id.navigation)

        //endregion

        //region ========================================= Listener =========================================

        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.login_frame -> {
                    startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.sign_up_frame -> {
                }
                R.id.sign_up_socials_frame -> {
                }
            }
            false
        }

        bottomNavigationView!!.menu.getItem(1).isChecked = true
        bottomNavigationView!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        //endregion
    }
}
