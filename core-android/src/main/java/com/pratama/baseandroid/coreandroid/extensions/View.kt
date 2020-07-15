package com.pratama.baseandroid.coreandroid.extensions

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

fun View.toVisible() {
    this.visibility = VISIBLE
}

fun View.toGone() {
    this.visibility = GONE
}