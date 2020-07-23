package com.pratama.baseandroid.data.datasource.local

import com.pratama.baseandroid.domain.entity.News

interface NewsLocalDatasource {
   suspend fun insertNews(news: List<News>)
   suspend fun getAllNews(): List<News>
}
