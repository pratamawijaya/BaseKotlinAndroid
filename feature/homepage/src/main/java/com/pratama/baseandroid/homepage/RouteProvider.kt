package com.pratama.baseandroid.homepage

import android.content.Intent
import com.github.florent37.application.provider.Provider
import com.pratama.baseandroid.homepage.ui.HomePageActivity
import com.pratama.baseandroid.routing.Routes

class RouteProvider : Provider() {
    override fun provide() {
        Routes.HomePage.register { ctx -> Intent(ctx, HomePageActivity::class.java) }
    }
}