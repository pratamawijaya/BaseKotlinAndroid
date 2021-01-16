package com.pratama.baseandroid.domain.entity

import com.pratama.baseandroid.ui.dto.NewsSourceDto

data class NewsSource(
    val id: String,
    val name: String
)

fun NewsSource.toDto(): NewsSourceDto {
    return NewsSourceDto(id, name)
}