package com.pratama.baseandroid.data.datasource.local

import com.pratama.baseandroid.data.datasource.local.db.AppDatabase
import com.pratama.baseandroid.domain.entity.News

class NewsLocalDatasourceImpl(private val appDatabase: AppDatabase) : NewsLocalDatasource {

    override suspend fun insertNews(news: List<News>) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllNews(): List<News> {
        val response = appDatabase.newsDao().getAllNews()
        return emptyList()
    }
}