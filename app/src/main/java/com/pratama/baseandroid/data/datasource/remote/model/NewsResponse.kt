package com.pratama.baseandroid.data.datasource.remote.model

import com.pratama.baseandroid.domain.entity.News
import com.pratama.baseandroid.domain.entity.NewsSource

data class NewsResponse(
    val source: SourceResponse,
    val author: String? = "",
    val title: String? = "",
    val description: String? = "",
    val url: String? = "",
    val urlToImage: String? = "",
    val publishedAt: String? = ""
)

data class SourceResponse(
    val id: String? = "",
    val name: String? = ""
)


fun toNews(newsResponse: NewsResponse): News {
    return News(
        author = newsResponse.author ?: "",
        title = newsResponse.title ?: "",
        description = newsResponse.description ?: "",
        url = newsResponse.url ?: "",
        urlToImage = newsResponse.urlToImage ?: "",
        publishedAt = newsResponse.publishedAt ?: "",
        source = toNewsSource(newsResponse.source)
    )
}

fun toNewsSource(source: SourceResponse): NewsSource {
    return NewsSource(
        id = source.id ?: "",
        name = source.name ?: ""
    )
}