package com.pratama.baseandroid.data.datasource.remote.model

import com.pratama.baseandroid.domain.entity.News

data class TopHeadlineResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsResponse>
)

fun TopHeadlineResponse.toNewsList(): List<News> {
    val listNews = mutableListOf<News>()
    articles.map {
        listNews.add(toNews((it)))
    }
    return listNews
}

