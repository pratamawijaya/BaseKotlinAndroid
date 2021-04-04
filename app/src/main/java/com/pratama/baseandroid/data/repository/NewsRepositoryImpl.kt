package com.pratama.baseandroid.data.repository

import com.github.ajalt.timberkt.d
import com.pratama.baseandroid.coreandroid.base.network.Result
import com.pratama.baseandroid.coreandroid.base.network.networkBoundResource
import com.pratama.baseandroid.coreandroid.exception.Failure
import com.pratama.baseandroid.coreandroid.functional.Either
import com.pratama.baseandroid.coreandroid.network.NetworkChecker
import com.pratama.baseandroid.data.datasource.local.NewsLocalDatasource
import com.pratama.baseandroid.data.datasource.remote.NewsRemoteDatasource
import com.pratama.baseandroid.domain.entity.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.io.IOException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remote: NewsRemoteDatasource,
    private val local: NewsLocalDatasource,
    private val networkChecker: NetworkChecker
) : NewsRepository {

    override suspend fun getTopHeadlinesFlow(
        forceUpdate: Boolean,
        country: String,
        category: String
    ): Flow<Result<List<News>>> {
        return networkBoundResource(
            query = {
                val data = local.getAllNews()
                flowOf(data)
            },
            fetch = {
                val response = remote.getTopHeadlines(category, country)
                if (!response.isNullOrEmpty()) {
                    response
                } else {
                    emptyList()
                }
            },
            saveFetchResult = {
                local.insertNews(it)
            },
            shouldFetch = {
                forceUpdate || it.isNullOrEmpty()
            }
        )
    }

    override suspend fun getTopHeadlines(
        country: String,
        category: String
    ): Either<Failure, List<News>> {
        return try {
            if (networkChecker.isNetworkConnected()) {
                d { "connection : connect to internet" }
                // connected to internet
                val response = remote.getTopHeadlines(category = category, country = country)

                local.insertNews(response)

                Either.Right(response)
            } else {
                d { "connection : disconnect" }
                // not connected
                val localNews = local.getAllNews()
                d { "get data from local: ${localNews.size}" }
                if (localNews.isEmpty()) {
                    Either.Left(Failure.LocalDataNotFound)
                } else {
                    Either.Right(localNews)
                }
            }
        } catch (ex: IOException) {
            Either.Left(Failure.ServerError(ex.localizedMessage))
        }
    }
}
