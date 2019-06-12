package com.pratamawijaya.basekotlin.data.repository

import com.pratamawijaya.basekotlin.domain.Article
import io.reactivex.Single

interface NewsRepository {
    fun getTopHeadlines(): Single<List<Article>>
    fun getEverything(forceUpdate: Boolean = false, query: String, page: Int): Single<List<Article>>
}