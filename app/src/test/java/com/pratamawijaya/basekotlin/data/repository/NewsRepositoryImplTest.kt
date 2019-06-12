package com.pratamawijaya.basekotlin.data.repository

import com.nhaarman.mockitokotlin2.whenever
import com.pratamawijaya.basekotlin.data.ArticleFactory
import com.pratamawijaya.basekotlin.di.myAppModule
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.mock.declareMock

@RunWith(JUnit4::class)
class NewsRepositoryImplTest : KoinTest {

    @Before
    fun setUp() {
        startKoin {
            modules(myAppModule)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `test get top headlines complete`() {
        declareMock<NewsRepository> {
            whenever(this.getTopHeadlines()).thenReturn(Single.just(emptyList()))
            val testObs = this.getTopHeadlines().test()
            testObs.assertComplete()
        }
    }

    @Test
    fun `test get top headlines error`() {
        val errorResponse = Throwable("Error Response")

        declareMock<NewsRepository> {
            whenever(this.getTopHeadlines()).thenReturn(Single.error(errorResponse))
            val testObserver = this.getTopHeadlines().test()
            testObserver.assertError(errorResponse)
        }
    }

    @Test
    fun `test get top headlines return response`() {
        val response = ArticleFactory.makeListArticleDomain()

        declareMock<NewsRepository> {
            whenever(this.getTopHeadlines()).thenReturn(Single.just(response))

            val testObs = this.getTopHeadlines().test()
            testObs.assertValue(response)
        }
    }

    @Test
    fun `test get everything complete`() {
        declareMock<NewsRepository> {
            whenever(this.getEverything(false, "berita", 1)).thenReturn(Single.just(emptyList()))
            val testObserver = this.getEverything(false, "berita", 1).test()
            testObserver.assertComplete()
        }
    }

    @Test
    fun `test get everything error`() {
        val errorResponse = Throwable("Error")

        declareMock<NewsRepository> {
            whenever(this.getEverything(forceUpdate = false, query = "berita", page = 1)).thenReturn(Single.error(errorResponse))

            val testObs = this.getEverything(forceUpdate = false, query = "berita", page = 1).test()
            testObs.assertError(errorResponse)
        }
    }

    @Test
    fun `test get everything return response`() {
        val response = ArticleFactory.makeListArticleDomain()

        declareMock<NewsRepository> {
            whenever(this.getEverything(forceUpdate = false, query = "berita", page = 1)).thenReturn(Single.just(response))

            val testObs = this.getEverything(forceUpdate = false, query = "berita", page = 1).test()
            testObs.assertValue(response)
        }
    }
}