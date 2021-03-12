package com.polish.registernow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // start an intent to navigate to the Mainactivity
        startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
        finish()

    }
}