package com.pratamawijaya.basekotlin.presentation.home

import com.pratamawijaya.basekotlin.R
import com.pratamawijaya.basekotlin.di.component.ActivityComponent
import com.pratamawijaya.basekotlin.presentation.base.BaseInjectedFragment

/**
 * Created by pratama on 8/4/17.
 */
class HomeFragment : BaseInjectedFragment() {

    override fun injectModule(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun getLayout(): Int = R.layout.view_home

}