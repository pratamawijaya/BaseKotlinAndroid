package com.pratamawijaya.basekotlin.presentation.utils

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by pratama on 10/3/17.
 */

@BindingAdapter("bind:loadUrl")
fun loadImage(img: ImageView, url: String) {
    Picasso.with(img.context)
            .load(url)
            .into(img)
}
