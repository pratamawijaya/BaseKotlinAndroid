package com.pratamawijaya.basekotlin

import android.app.Application
import android.content.Context
import android.util.Log
import com.pratamawijaya.basekotlin.di.component.AppComponent
import com.pratamawijaya.basekotlin.di.component.DaggerAppComponent
import com.pratamawijaya.basekotlin.di.module.AppModule
import com.pratamawijaya.basekotlin.di.module.DataModule
import timber.log.Timber

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

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashlyticsTree())
        }
    }

    class CrashlyticsTree : Timber.Tree() {

        override fun isLoggable(tag: String?, priority: Int): Boolean = priority >= Log.ERROR

        override fun log(priority: Int, tag: String?, message: String?, throwable: Throwable?) {
//            Crashlytics.log(priority, tag, message)

            if (throwable != null) {
//                Crashlytics.logException(throwable)
            }
        }

    }
}