package com.pratama.baseandroid.splash

import android.content.Intent
import com.github.florent37.application.provider.Provider
import com.pratama.baseandroid.routing.Routes
import com.pratama.baseandroid.splash.ui.SplashActivity

class RouteProvider : Provider() {
    override fun provide() {
        Routes.Splash.register { ctx ->
            Intent(ctx, SplashActivity::class.java)
        }
    }

}