package com.pratama.baseandroid.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pratama.baseandroid.R
import com.pratama.baseandroid.ui.homepage.HomePageActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startActivity(Intent(this, HomePageActivity::class.java))
    }
}