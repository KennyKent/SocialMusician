package com.suonk.socialmusician.controller.activity

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView

import com.suonk.socialmusician.R

class SplashScreenActivity : AppCompatActivity() {

    private val SPLASH_DISPLAY_LENGHT = 3000
    private var splash_screen_MsnLogo: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT

        splash_screen_MsnLogo = findViewById(R.id.splash_screen_MSN_logo)

        val slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up)

        Handler().postDelayed({
            startActivity(Intent(this@SplashScreenActivity, SignInActivity::class.java))
            finish()
//            splash_screen_MsnLogo!!.startAnimation(slideUp)
        }, SPLASH_DISPLAY_LENGHT.toLong())
    }
}
