package com.pratamawijaya.basekotlin.data.repository

import com.pratamawijaya.basekotlin.data.OpenDotaServices
import com.pratamawijaya.basekotlin.entity.Hero
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by pratama on 8/7/17.
 */
class HeroRepositoryImpl @Inject constructor(val services: OpenDotaServices) : HeroRepository {

    override fun getHeroes(): Observable<List<Hero>> {
        return services.getHeroes()
                .flatMap { Observable.fromIterable(it) }
                .map {
                    val heroesName = it.name?.replace("npc_dota_hero_", "")
                    val heroesImage = "http://cdn.dota2.com/apps/dota2/images/heroes/$heroesName" + "_full.png"
                    Hero(id = it.id,
                            name = it.name,
                            localName = it.localizedName,
                            primaryAttr = it.primaryAttr,
                            legs = it.legs,
                            roles = it.roles,
                            attackType = it.attackType,
                            heroesImage = heroesImage)
                }
                .toList()
                .toObservable()
    }
}