package com.pratama.baseandroid.homepage.data.repository

import com.pratama.baseandroid.homepage.domain.entity.News

interface NewsRepository {
    fun getNewsList(): List<News>
}