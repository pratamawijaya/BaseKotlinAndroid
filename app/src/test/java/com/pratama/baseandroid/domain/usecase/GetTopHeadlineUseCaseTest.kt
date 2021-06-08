package com.pratama.baseandroid.domain.usecase

import com.pratama.baseandroid.coreandroid.exception.Failure
import com.pratama.baseandroid.coreandroid.functional.Either
import com.pratama.baseandroid.data.repository.NewsRepository
import com.pratama.baseandroid.domain.entity.News
import com.pratama.baseandroid.domain.entity.NewsSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetTopHeadlineUseCaseTest {

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    private val testScope = TestCoroutineScope(testDispatcher)

    @MockK
    lateinit var newsRepo: NewsRepository

    lateinit var useCase: GetTopHeadlineUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetTopHeadlineUseCase(newsRepo)
    }

    @Test
    fun `test usecaseRun return failure`() {
        // given
        coEvery {
            newsRepo.getTopHeadlines(
                country = "us",
                category = "tech"
            )
        } returns generateFailure()

        val params = GetTopHeadlineUseCase.TopHeadlineParam(country = "us", category = "tech")

        testScope.launch {
            val result = useCase.run(params)
            assertTrue(result.isLeft)
        }
    }

    @Test
    fun `test usecaseRun return value`() {
        // given
        coEvery {
            newsRepo.getTopHeadlines(
                country = "us",
                category = "tech"
            )
        } returns generateFakeNews()

        val params = GetTopHeadlineUseCase.TopHeadlineParam(country = "us", category = "tech")

        testScope.launch {
            val result = useCase.run(params)
            assertTrue(result.isRight)
        }
    }

    private fun generateFailure(): Either<Failure, List<News>> {
        return Either.Left(Failure.ServerError("error"))
    }

    private fun generateFakeNews(): Either<Failure, List<News>> {
        return Either.Right(
            listOf(
                News(
                    source = NewsSource(id = "1", name = "title"),
                    author = "",
                    title = "",
                    description = "",
                    urlToImage = "",
                    url = "",
                    publishedAt = ""
                )
            )
        )
    }
}