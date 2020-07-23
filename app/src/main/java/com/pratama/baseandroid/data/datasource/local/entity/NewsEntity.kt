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

fun News.toNewsEntity(): NewsEntity {
    return NewsEntity(
        title = this.title,
        author = this.author,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt,
        source = this.source.name
    )
}

fun NewsEntity.toNews(): News {
    return News(
        source = NewsSource("", this.source ?: ""),
        author = this.author ?: "",
        title = this.title ?: "",
        description = this.description ?: "",
        urlToImage = this.urlToImage ?: "",
        url = this.url ?: "",
        publishedAt = this.publishedAt ?: ""
    )
}
