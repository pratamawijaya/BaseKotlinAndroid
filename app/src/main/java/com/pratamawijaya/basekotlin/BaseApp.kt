package com.pratamawijaya.basekotlin

import android.app.Application
import android.content.Context
import com.pratamawijaya.basekotlin.di.component.AppComponent
import com.pratamawijaya.basekotlin.di.component.DaggerAppComponent
import com.pratamawijaya.basekotlin.di.module.AppModule
import com.pratamawijaya.basekotlin.di.module.DataModule

/**
 * Created by pratama on 8/4/17.
 */
class BaseApp : Application() {

    companion object {
        fun get(context: Context): BaseApp = context.applicationContext as BaseApp
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .dataModule(DataModule())
                .build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}