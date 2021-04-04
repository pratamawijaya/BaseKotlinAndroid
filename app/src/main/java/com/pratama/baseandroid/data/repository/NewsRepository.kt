package com.pratama.baseandroid.data.repository

import com.pratama.baseandroid.coreandroid.base.network.Result
import com.pratama.baseandroid.coreandroid.exception.Failure
import com.pratama.baseandroid.coreandroid.functional.Either
import com.pratama.baseandroid.domain.entity.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getTopHeadlines(country: String, category: String): Either<Failure, List<News>>
    suspend fun getTopHeadlinesFlow(
        forceUpdate: Boolean = false,
        country: String,
        category: String
    ): Flow<Result<List<News>>>
}