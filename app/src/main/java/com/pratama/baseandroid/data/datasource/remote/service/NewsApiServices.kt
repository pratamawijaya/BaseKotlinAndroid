package com.pratama.baseandroid.data.datasource.remote.service

import com.pratama.baseandroid.data.datasource.remote.model.TopHeadlineResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiServices {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("category") category: String
    ): TopHeadlineResponse
}