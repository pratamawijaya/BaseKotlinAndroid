package com.pratama.baseandroid.data.datasource.remote

import com.pratama.baseandroid.data.datasource.remote.model.toNewsList
import com.pratama.baseandroid.data.datasource.remote.service.NewsApiServices
import com.pratama.baseandroid.domain.entity.News
import javax.inject.Inject

class NewsRemoteDatasourceImpl @Inject constructor(private val services: NewsApiServices) :
    NewsRemoteDatasource {

    override suspend fun getTopHeadlines(category: String, country: String): List<News> {
        return services.getTopHeadlines(country, category).toNewsList()
    }
}