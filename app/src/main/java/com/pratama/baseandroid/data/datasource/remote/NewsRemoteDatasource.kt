package com.pratama.baseandroid.data.datasource.remote

import com.pratama.baseandroid.data.datasource.remote.model.TopHeadlineResponse

interface NewsRemoteDatasource {
    suspend fun getTopHeadlines(category: String, country: String): TopHeadlineResponse
}