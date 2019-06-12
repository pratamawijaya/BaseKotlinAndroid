package com.pratamawijaya.basekotlin.data.repository

import com.pratamawijaya.basekotlin.data.mapper.ArticleMapper
import com.pratamawijaya.basekotlin.data.services.NewsServices
import com.pratamawijaya.basekotlin.domain.Article
import io.reactivex.Single

open class NewsRepositoryImpl(private val service: NewsServices, private val articleMapper: ArticleMapper) : NewsRepository {

    override fun getTopHeadlines(): Single<List<Article>> {
        return service.getTopHeadlines("id")
                .map { articleMapper.mapToListDomain(it.articles) }
    }

    override fun getEverything(forceUpdate: Boolean, query: String, page: Int): Single<List<Article>> {
        return service.getEverything(query, page, pageSize = 20)
                .map { articleMapper.mapToListDomain(it.articles) }
    }
}