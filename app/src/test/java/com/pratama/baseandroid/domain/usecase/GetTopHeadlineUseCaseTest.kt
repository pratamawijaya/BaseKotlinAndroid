package com.pratama.baseandroid.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pratama.baseandroid.coreandroid.exception.Failure
import com.pratama.baseandroid.coreandroid.functional.Either
import com.pratama.baseandroid.data.repository.NewsRepository
import com.pratama.baseandroid.domain.entity.News
import com.pratama.baseandroid.domain.entity.NewsSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetTopHeadlineUseCaseTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var newsRepo: NewsRepository

    lateinit var useCase: GetTopHeadlineUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetTopHeadlineUseCase(newsRepo)
    }

    @Test
    fun `test usecaseRun return failure`() = runBlockingTest {
        // given
        coEvery {
            newsRepo.getTopHeadlines(
                country = "us",
                category = "tech"
            )
        } returns generateFailure()

        val params = GetTopHeadlineUseCase.TopHeadlineParam(country = "us", category = "tech")

        val result = useCase.run(params)

        assertTrue(result.isLeft)
    }

    @Test
    fun `test usecaseRun return value`() = runBlockingTest {
        // given
        coEvery {
            newsRepo.getTopHeadlines(
                country = "us",
                category = "tech"
            )
        } returns generateFakeNews()

        val params = GetTopHeadlineUseCase.TopHeadlineParam(country = "us", category = "tech")

        val result = useCase.run(params)

        assertTrue(result.isRight)
    }

    private fun generateFailure(): Either<Failure, List<News>> {
        return Either.Left(Failure.ServerError)
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