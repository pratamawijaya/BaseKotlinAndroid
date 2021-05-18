package com.pratama.baseandroid

import android.os.Bundle
import android.view.LayoutInflater
import com.pratama.baseandroid.databinding.ActivityMainBinding
import pratama.library.core_android.activity.BaseActivityBinding

class MainActivity : BaseActivityBinding<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun setupView(binding: ActivityMainBinding) {
        // todo:
    }
}