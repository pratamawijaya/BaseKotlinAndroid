package com.pratama.baseandroid.data.datasource.remote

import com.pratama.baseandroid.data.datasource.remote.model.NewsResponse
import com.pratama.baseandroid.data.datasource.remote.model.SourceResponse
import com.pratama.baseandroid.data.datasource.remote.model.TopHeadlineResponse
import com.pratama.baseandroid.data.datasource.remote.service.NewsApiServices
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

class NewsRemoteKoTest : BehaviorSpec({

    lateinit var newsRemoteDatasourceImpl: NewsRemoteDatasourceImpl

    @MockK
    lateinit var service: NewsApiServices

    beforeTest {
        MockKAnnotations.init(this)
        newsRemoteDatasourceImpl = NewsRemoteDatasourceImpl(service)
    }

    Given("news remote datasource") {

        coEvery { service.getTopHeadlines(country = "", category = "") } returns generateFakeNews()

        When("news fetch from api") {
            val result = newsRemoteDatasourceImpl.getTopHeadlines(category = "", country = "")

            And("internet NOT available") {
                Then("should return error") {
                }
            }

            And("internet available") {
                Then("return news list") {
                    result.size shouldBe 1
                }
            }
        }

    }
})

private fun generateFakeNews(): TopHeadlineResponse {
    return TopHeadlineResponse(
        status = "success",
        totalResults = 1,
        articles = listOf(
            NewsResponse(
                source = SourceResponse(id = "1", name = "ok")
            )
        )
    )
}