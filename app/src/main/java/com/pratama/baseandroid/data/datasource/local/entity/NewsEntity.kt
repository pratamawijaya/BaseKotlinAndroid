package com.pratama.baseandroid.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

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