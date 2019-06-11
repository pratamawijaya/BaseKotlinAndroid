package com.pratamawijaya.basekotlin.data.mapper

import com.pratamawijaya.basekotlin.data.model.ArticleModel
import com.pratamawijaya.basekotlin.domain.Article

class ArticleMapper : BaseMapper<ArticleModel, Article> {

    override fun mapToDomain(model: ArticleModel): Article {
        return Article(
                title = model.title,
                author = model.author ?: "",
                content = model.content ?: "",
                url = model.url,
                imageUrl = model.urlToImage ?: ""
        )
    }

    override fun mapToModel(domain: Article): ArticleModel {
        return ArticleModel(
                title = domain.title,
                url = domain.url,
                content = domain.content,
                author = domain.author,
                urlToImage = domain.imageUrl
        )
    }
}