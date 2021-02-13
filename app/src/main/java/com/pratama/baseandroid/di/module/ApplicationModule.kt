package com.pratama.baseandroid.di.module

import android.content.Context
import androidx.room.Room
import com.pratama.baseandroid.data.datasource.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext ctx: Context): AppDatabase {
        return Room.databaseBuilder(
            ctx,
            AppDatabase::class.java, "my_app_database"
        ).build()
    }

}