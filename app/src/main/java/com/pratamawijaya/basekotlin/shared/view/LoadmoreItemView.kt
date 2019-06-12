package com.pratamawijaya.basekotlin.shared.view

import com.pratamawijaya.basekotlin.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder

class LoadmoreItemView : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
    }

    override fun getLayout(): Int = R.layout.layout_item_loadmore_loading
}