package com.pratamawijaya.basekotlin.screens.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pratamawijaya.basekotlin.R
import com.pratamawijaya.basekotlin.screens.home.HomeActivity
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startActivity<HomeActivity>()
        finish()
    }
}
