package com.pratama.baseandroid.di.module

import android.content.Context
import com.pratama.baseandroid.BuildConfig
import com.pratama.baseandroid.coreandroid.network.NetworkChecker
import com.pratama.baseandroid.coreandroid.network.NetworkCheckerImpl
import com.pratama.baseandroid.data.datasource.local.NewsLocalDatasource
import com.pratama.baseandroid.data.datasource.local.NewsLocalDatasourceImpl
import com.pratama.baseandroid.data.datasource.local.db.AppDatabase
import com.pratama.baseandroid.data.datasource.remote.NewsRemoteDatasource
import com.pratama.baseandroid.data.datasource.remote.NewsRemoteDatasourceImpl
import com.pratama.baseandroid.data.datasource.remote.interceptor.HeaderInterceptor
import com.pratama.baseandroid.data.datasource.remote.service.NewsApiServices
import com.pratama.baseandroid.data.repository.NewsRepository
import com.pratama.baseandroid.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    @Provides
    @ActivityScoped
    fun provideOkHttpClient(headerInterceptor: HeaderInterceptor) = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(headerInterceptor)
            .build()
    } else {
        OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .build()
    }

    @Provides
    @ActivityScoped
    fun provideHeaderInterceptor(): HeaderInterceptor {
        return HeaderInterceptor()
    }

    @Provides
    @ActivityScoped
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @ActivityScoped
    fun provideNewsApiServices(retrofit: Retrofit): NewsApiServices =
        retrofit.create(NewsApiServices::class.java)

    @Provides
    @ActivityScoped
    fun provideNewsRemoteDatasource(services: NewsApiServices): NewsRemoteDatasource {
        return NewsRemoteDatasourceImpl(services)
    }

    @Provides
    @ActivityScoped
    fun provideNewsLocalDatasource(appDatabase: AppDatabase): NewsLocalDatasource {
        return NewsLocalDatasourceImpl(appDatabase)
    }


    @Provides
    @ActivityScoped
    fun provideNetworkChecker(@ApplicationContext ctx: Context): NetworkChecker {
        return NetworkCheckerImpl(ctx)
    }

    @Provides
    @ActivityScoped
    fun provideNewsRepository(
        remote: NewsRemoteDatasource,
        local: NewsLocalDatasource,
        networkCheck: NetworkChecker
    ): NewsRepository {
        return NewsRepositoryImpl(remote = remote, local = local, networkChecker = networkCheck)
    }
}