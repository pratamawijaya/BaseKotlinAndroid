package com.pratamawijaya.basekotlin.data.model

data class ArticleModel(
        val title: String,
        val author: String?,
        val url: String,
        val content: String?,
        val urlToImage:String?
)