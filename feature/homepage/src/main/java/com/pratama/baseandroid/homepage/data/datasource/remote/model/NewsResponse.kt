package com.pratama.baseandroid.homepage.data.datasource.remote.model

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