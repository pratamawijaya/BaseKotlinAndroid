package com.pratamawijaya.basekotlin.presentation.home

import android.os.Bundle
import android.util.Log
import com.pratamawijaya.basekotlin.R
import com.pratamawijaya.basekotlin.di.component.ActivityComponent
import com.pratamawijaya.basekotlin.presentation.base.BaseInjectedActivity

class MainActivity : BaseInjectedActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val names = listOf("Cersei Lannister",
                "The Mountain",
                "The Red Women",
                "Beric Dondarion",
                "Thoros of Myr",
                "Ilyn Payne",
                "The Hound",
                "Walder Pray",
                "Meryn Trant",
                "Joffrey",
                "Twin Lannister",
                "Polliver",
                "Rorge")

        names.map { it.toUpperCase() }
                .take(4)
                .map { Log.d("debug", "$it") }

    }

    override fun injectModule(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }
}
