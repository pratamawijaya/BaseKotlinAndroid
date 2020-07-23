package com.pratama.baseandroid.data.datasource.local

import com.github.ajalt.timberkt.d
import com.pratama.baseandroid.data.datasource.local.db.AppDatabase
import com.pratama.baseandroid.data.datasource.local.entity.toNews
import com.pratama.baseandroid.data.datasource.local.entity.toNewsEntity
import com.pratama.baseandroid.domain.entity.News

class NewsLocalDatasourceImpl(private val appDatabase: AppDatabase) : NewsLocalDatasource {

    override suspend fun insertNews(news: List<News>) {
        news.map {
            d { "insert to local data ${it.title}" }
            appDatabase.newsDao().insert(it.toNewsEntity())
        }
    }

    override suspend fun getAllNews(): List<News> {
        val localNews = appDatabase.newsDao().getAllNews()
        d { "local news size ${localNews.size}" }
        val listNews = mutableListOf<News>()
        localNews.map {
            listNews.add(it.toNews())
        }
        return listNews
    }
}
