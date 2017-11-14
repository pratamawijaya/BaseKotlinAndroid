package com.pratamawijaya.basekotlin

import android.app.Application
import android.content.Context
import android.util.Log
import com.pratamawijaya.basekotlin.di.component.AppComponent
import com.pratamawijaya.basekotlin.di.component.DaggerAppComponent
import com.pratamawijaya.basekotlin.di.module.AppModule
import com.pratamawijaya.basekotlin.di.module.DataModule
import timber.log.Timber
import java.util.regex.Pattern

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

    class LinkingDebugTree : Timber.Tree() {
        companion object {
            val CALL_STACK_INDEX = 4
            val ANONYMOUS_CLASS: Pattern = Pattern.compile("(\\\\\$\\\\d+)+\$")
        }

        override fun log(priority: Int, tag: String?, message: String?, t: Throwable?) {
            val stackTrace = Throwable().stackTrace

            if (stackTrace.size <= CALL_STACK_INDEX) {
                throw IllegalStateException("Synthetic stacktrace didn't have enough elements: are you using proguard?")
            }

            val clazz = extractClassName(stackTrace[CALL_STACK_INDEX])
            val linenumber = stackTrace[CALL_STACK_INDEX].lineNumber
            val outMessage = ".($clazz.java:$linenumber) - $message"
            super.log(priority, tag, outMessage, t)
        }

        /**
         * Extract the class name without any anonymous class suffixes (e.g., {@code Foo$1}
         * becomes {@code Foo}).
         */
        private fun extractClassName(stackTraceElement: StackTraceElement): String {
            var tag = stackTraceElement.className
            val matcher = ANONYMOUS_CLASS.matcher(tag)
            if (matcher.find()) {
                tag = matcher.replaceAll("")
            }
            return tag.substring(tag.lastIndexOf('.') + 1)
        }
    }
}