package com.pratamawijaya.basekotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

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
}
