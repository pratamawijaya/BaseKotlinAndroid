package com.pratamawijaya.basekotlin.presentation.home

import com.pratamawijaya.basekotlin.entity.Hero
import com.pratamawijaya.basekotlin.presentation.base.BaseView

/**
 * Created by pratama on 8/7/17.
 */
interface MainView : BaseView {
    fun displayHeroes(heroes: List<Hero>)
}