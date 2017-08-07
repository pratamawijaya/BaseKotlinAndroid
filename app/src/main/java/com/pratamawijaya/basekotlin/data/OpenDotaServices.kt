package com.pratamawijaya.basekotlin.data

import com.pratamawijaya.basekotlin.data.model.HeroModel
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by pratama on 8/7/17.
 */
interface OpenDotaServices {
    @GET("heroes")
    fun getHeroes(): Observable<List<HeroModel>>
}