package com.pratamawijaya.basekotlin.di

import com.google.gson.GsonBuilder
import com.pratamawijaya.basekotlin.data.mapper.ArticleMapper
import com.pratamawijaya.basekotlin.data.repository.NewsRepository
import com.pratamawijaya.basekotlin.data.repository.NewsRepositoryImpl
import com.pratamawijaya.basekotlin.data.services.NewsInterceptor
import com.pratamawijaya.basekotlin.data.services.NewsServices
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single { NewsInterceptor() }
    single { createOkHttpClient(get()) }
    single { createWebService<NewsServices>(get(), "") }
}

val dataModule = module {

    // repository
    single { NewsRepositoryImpl(get(), get()) as NewsRepository }

    // mapper
    single { ArticleMapper() }
}

fun createOkHttpClient(interceptor: NewsInterceptor): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(interceptor)
            .build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create()

    val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    return retrofit.create(T::class.java)
}

val myAppModule = listOf(appModule, dataModule)