package com.pratamawijaya.basekotlin.presentation.utils

import android.app.Activity
import android.app.Fragment
import android.view.View
import android.widget.Toast
import com.pratamawijaya.basekotlin.BuildConfig

/**
 * Created by pratama on 04/12/17.
 */

/**
 * show toast
 */
fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    if (BuildConfig.DEBUG) Toast.makeText(this, message, duration).show()
}

fun Fragment.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    if (BuildConfig.DEBUG) Toast.makeText(this.activity, message, duration).show()
}

/**
 * set visible view
 */
fun View.toVisible() {
    visibility = View.VISIBLE
}

/**
 * set gone
 */
fun View.toGone() {
    visibility = View.GONE
}