package com.pratamawijaya.basekotlin.data.repository

import com.pratamawijaya.basekotlin.domain.Article
import io.reactivex.Single

interface NewsRepository {
    fun getArticles(): Single<List<Article>>
}