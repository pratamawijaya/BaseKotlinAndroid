package com.pratama.baseandroid.data.datasource.local

import com.pratama.baseandroid.data.datasource.local.db.AppDatabase
import com.pratama.baseandroid.data.datasource.local.entity.toNews
import com.pratama.baseandroid.data.datasource.local.entity.toNewsEntity
import com.pratama.baseandroid.domain.entity.News

class NewsLocalDatasourceImpl(private val appDatabase: AppDatabase) : NewsLocalDatasource {

    override suspend fun insertNews(news: List<News>) {
        news.map {
            appDatabase.newsDao().insert(it.toNewsEntity())
        }
    }

    override suspend fun getAllNews(): List<News> {
        val localNews = appDatabase.newsDao().getAllNews()
        val listNews = mutableListOf<News>()
        localNews.map {
            listNews.add(it.toNews())
        }
        return listNews
    }
}