package com.pratamawijaya.basekotlin.data.repository

import com.pratamawijaya.basekotlin.entity.Hero
import io.reactivex.Observable

/**
 * Created by pratama on 8/7/17.
 */
interface HeroRepository {
    fun getHeroes(): Observable<List<Hero>>
}