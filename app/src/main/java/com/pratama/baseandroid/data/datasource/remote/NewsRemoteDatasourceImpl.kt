package com.pratama.baseandroid.data.datasource.remote

import com.pratama.baseandroid.data.datasource.remote.model.TopHeadlineResponse
import com.pratama.baseandroid.data.datasource.remote.service.NewsApiServices

class NewsRemoteDatasourceImpl(private val services: NewsApiServices) : NewsRemoteDatasource {
    override suspend fun getTopHeadlines(category: String, country: String): TopHeadlineResponse {
        return services.getTopHeadlines(country, category)
    }
}