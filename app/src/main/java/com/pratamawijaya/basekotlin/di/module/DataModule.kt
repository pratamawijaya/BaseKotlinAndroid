package com.pratamawijaya.basekotlin.di.module

import com.pratamawijaya.basekotlin.data.OpenDotaServices
import com.pratamawijaya.basekotlin.data.repository.HeroRepository
import com.pratamawijaya.basekotlin.data.repository.HeroRepositoryImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by pratama on 8/4/17.
 */
@Module
class DataModule {

    @Provides
    @Singleton fun provideOpenDotaServices(retrofit: Retrofit): OpenDotaServices = retrofit.create(OpenDotaServices::class.java)

    @Provides
    @Singleton fun provideHeroRepo(heroRepositoryImpl: HeroRepositoryImpl): HeroRepository = heroRepositoryImpl

}