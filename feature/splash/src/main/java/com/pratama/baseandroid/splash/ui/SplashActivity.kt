package com.pratama.baseandroid.splash.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.florent37.navigator.Navigator
import com.pratama.baseandroid.routing.Routes
import com.pratama.baseandroid.splash.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        btnNext.setOnClickListener {
            Navigator.of(this).pushReplacement(Routes.HomePage)
        }
    }
}