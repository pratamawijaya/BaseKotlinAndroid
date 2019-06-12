package com.pratamawijaya.basekotlin.data

import com.pratamawijaya.basekotlin.DataFactory
import com.pratamawijaya.basekotlin.data.model.ArticleModel
import com.pratamawijaya.basekotlin.domain.Article

object ArticleFactory {
    fun makeArticleModel(): ArticleModel {
        return ArticleModel(
                title = DataFactory.randomString(),
                url = DataFactory.randomString(),
                urlToImage = DataFactory.randomString(),
                content = DataFactory.randomString(),
                author = DataFactory.randomString()
        )
    }

    fun makeArticleDomain(): Article {
        return Article(
                title = DataFactory.randomString(),
                url = DataFactory.randomString(),
                imageUrl = DataFactory.randomString(),
                content = DataFactory.randomString(),
                author = DataFactory.randomString()
        )
    }

    fun makeListArticleModel(): List<ArticleModel> {
        return listOf(
                makeArticleModel(),
                makeArticleModel(),
                makeArticleModel()
        )
    }

    fun makeListArticleDomain(): List<Article>{
        return listOf(
                makeArticleDomain(),
                makeArticleDomain(),
                makeArticleDomain()
        )
    }
}