package com.pratama.baseandroid.coreandroid

import android.util.Log
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    init {
        Log.d("debug", "init")
    }
}
