package com.pratamawijaya.basekotlin.data.mapper

import com.pratamawijaya.basekotlin.data.database.entity.ArticleEntity
import com.pratamawijaya.basekotlin.data.model.ArticleModel
import com.pratamawijaya.basekotlin.domain.Article

open class ArticleMapper : BaseMapper<ArticleModel, Article> {

    override fun mapToDomain(model: ArticleModel): Article {
        return Article(
                title = model.title,
                author = model.author ?: "",
                content = model.content ?: "",
                url = model.url,
                imageUrl = model.urlToImage ?: ""
        )
    }

    fun mapToEntity(model: ArticleModel): ArticleEntity {
        return ArticleEntity(
                url = model.url,
                title = model.title,
                author = model.author ?: "",
                content = model.content ?: "",
                urlImage = model.urlToImage ?: ""
        )
    }

    fun mapToListEntity(models: List<ArticleModel>): List<ArticleEntity> {
        var listEntity = mutableListOf<ArticleEntity>()

        models.map {
            listEntity.add(mapToEntity(it))
        }

        return listEntity
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