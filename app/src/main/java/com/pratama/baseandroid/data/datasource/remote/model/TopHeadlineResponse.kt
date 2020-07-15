package com.pratama.baseandroid.data.datasource.remote.model

data class TopHeadlineResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsResponse>
)