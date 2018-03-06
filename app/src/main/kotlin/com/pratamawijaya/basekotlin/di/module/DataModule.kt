package com.pratamawijaya.basekotlin.di.module

import android.content.Context
import com.pratamawijaya.basekotlin.data.OpenDotaServices
import com.pratamawijaya.basekotlin.data.PreferencesManager
import com.pratamawijaya.basekotlin.data.repository.HeroRepository
import com.pratamawijaya.basekotlin.data.repository.HeroRepositoryImpl
import com.pratamawijaya.basekotlin.di.ApplicationContext
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
    @Singleton
    fun providePreferencesManager(@ApplicationContext context: Context): PreferencesManager = PreferencesManager(context)

    @Provides
    @Singleton
    fun provideOpenDotaServices(retrofit: Retrofit): OpenDotaServices = retrofit.create(OpenDotaServices::class.java)

    @Provides
    @Singleton
    fun provideHeroRepo(heroRepositoryImpl: HeroRepositoryImpl): HeroRepository = heroRepositoryImpl

}