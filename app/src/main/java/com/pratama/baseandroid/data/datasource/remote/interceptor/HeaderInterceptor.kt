package com.pratama.baseandroid.data.datasource.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("X-Api-Key", "4b4df2ea3a154950852b6fda536cfb7f").build()
        return chain.proceed(request)
    }
}
