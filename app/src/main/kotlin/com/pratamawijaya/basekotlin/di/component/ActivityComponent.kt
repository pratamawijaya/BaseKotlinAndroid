package com.pratamawijaya.basekotlin.di.component

import com.pratamawijaya.basekotlin.di.PerActivity
import com.pratamawijaya.basekotlin.di.module.ActivityModule
import com.pratamawijaya.basekotlin.presentation.home.HomeFragment
import com.pratamawijaya.basekotlin.presentation.home.MainActivity
import dagger.Subcomponent

/**
 * Created by pratama on 8/4/17.
 */
@PerActivity
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        fun activityModule(activityModule: ActivityModule): Builder

        fun build(): ActivityComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: HomeFragment)

}