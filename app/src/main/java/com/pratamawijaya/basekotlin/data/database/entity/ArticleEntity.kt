package com.pratamawijaya.basekotlin.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
data class ArticleEntity(
        @PrimaryKey
        @ColumnInfo(name = "url")
        val url: String,
        val title: String,
        val author: String,
        val content: String,
        val urlImage: String
)