package com.pratama.baseandroid.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pratama.baseandroid.domain.entity.News
import com.pratama.baseandroid.domain.entity.NewsSource

@Entity(tableName = "News")
data class NewsEntity(
    val title: String?,
    val author: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val source: String?
) {
    @PrimaryKey(autoGenerate = true)
    var newsId: Int = 0
}

fun NewsEntity.toNews(): News {
    return News(
        source = NewsSource("", source ?: ""),
        author = author ?: "",
        title = title ?: "",
        description = description ?: "",
        urlToImage = urlToImage ?: "",
        url = url ?: "",
        publishedAt = publishedAt ?: ""
    )
}