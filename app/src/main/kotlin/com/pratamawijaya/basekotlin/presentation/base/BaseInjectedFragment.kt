package com.pratamawijaya.basekotlin.presentation.base

import android.os.Bundle
import com.pratamawijaya.basekotlin.BaseApp
import com.pratamawijaya.basekotlin.di.component.ActivityComponent
import com.pratamawijaya.basekotlin.di.module.ActivityModule

/**
 * Created by pratama on 8/4/17.
 */
abstract class BaseInjectedFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val act = activity
        if (act != null) {
            val activityComponent = BaseApp.get(act)
                    .appComponent.activityComponent()
                    .activityModule(ActivityModule(act))
                    .build()

            injectModule(activityComponent)
        }

    }

    abstract fun injectModule(activityComponent: ActivityComponent)
}