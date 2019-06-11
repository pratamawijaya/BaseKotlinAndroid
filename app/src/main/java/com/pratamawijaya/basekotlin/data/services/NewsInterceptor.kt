package com.pratamawijaya.basekotlin.data.services

import com.pratamawijaya.basekotlin.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class NewsInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
                .addHeader("X-Api-Key", BuildConfig.API_KEY)
                .build()
        return chain.proceed(request)
    }
}