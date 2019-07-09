package com.pratamawijaya.basekotlin.di

import androidx.room.Room
import com.google.gson.GsonBuilder
import com.pratamawijaya.basekotlin.data.database.AppDatabase
import com.pratamawijaya.basekotlin.data.database.PreferencesHelper
import com.pratamawijaya.basekotlin.data.mapper.ArticleMapper
import com.pratamawijaya.basekotlin.data.repository.NewsRepository
import com.pratamawijaya.basekotlin.data.repository.NewsRepositoryImpl
import com.pratamawijaya.basekotlin.data.services.NewsInterceptor
import com.pratamawijaya.basekotlin.data.services.NewsServices
import com.pratamawijaya.basekotlin.screens.home.HomeVM
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single { NewsInterceptor() }
    single { createOkHttpClient(get()) }
    single { createWebService<NewsServices>(get(), "https://newsapi.org/v2/") }
    single { PreferencesHelper(androidContext()) }

    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "app-database").build()
    }
}

val dataModule = module {

    // db dao
    single { get<AppDatabase>().articleDao() }

    // repository
    single { NewsRepositoryImpl(get(), get(), get()) as NewsRepository }

    // mapper
    single { ArticleMapper() }

    // viewmodel
    viewModel { HomeVM(get()) }
}

fun createOkHttpClient(interceptor: NewsInterceptor): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    val timeout = 60L
    return OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
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