package com.pratamawijaya.basekotlin.data.mapper

import com.pratamawijaya.basekotlin.data.ArticleFactory
import com.pratamawijaya.basekotlin.data.model.ArticleModel
import com.pratamawijaya.basekotlin.domain.Article
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class ArticleMapperTest {

    private lateinit var articleMapper: ArticleMapper

    @Before
    fun setUp() {
        articleMapper = ArticleMapper()
    }

    @Test
    fun `test map article model to domain`() {
        val articleModel = ArticleFactory.makeArticleModel()
        val articleDomain = articleMapper.mapToDomain(articleModel)

        assertEqualsData(articleModel, articleDomain)
    }


    @Test
    fun `test map article domain to model`() {
        val articleDomain = ArticleFactory.makeArticleDomain()
        val articleModel = articleMapper.mapToModel(articleDomain)

        assertEqualsData(articleModel, articleDomain)
    }

    private fun assertEqualsData(articleModel: ArticleModel, articleDomain: Article) {
        assertEquals(articleModel.title, articleDomain.title)
        assertEquals(articleModel.author, articleDomain.author)
        assertEquals(articleModel.content, articleDomain.content)
        assertEquals(articleModel.url, articleDomain.url)
        assertEquals(articleModel.urlToImage, articleDomain.imageUrl)
    }

}