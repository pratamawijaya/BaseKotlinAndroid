package com.pratama.baseandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.florent37.navigator.Navigator
import com.pratama.baseandroid.routing.Routes

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Navigator.of(this).pushReplacement(Routes.Splash)
    }
}