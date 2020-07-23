package com.pratama.baseandroid.coreandroid.extensions

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import com.squareup.picasso.Picasso

fun View.toVisible() {
    this.visibility = VISIBLE
}

fun View.toGone() {
    this.visibility = GONE
}

fun ImageView.loadFromUrl(url: String) {
    if (url.isEmpty()) return

    Picasso.get().load(url).into(this)
}