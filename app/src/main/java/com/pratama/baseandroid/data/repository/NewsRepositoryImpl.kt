package com.pratama.baseandroid.data.repository

import com.github.ajalt.timberkt.d
import com.github.ajalt.timberkt.e
import com.pratama.baseandroid.coreandroid.exception.Failure
import com.pratama.baseandroid.coreandroid.functional.Either
import com.pratama.baseandroid.coreandroid.network.NetworkChecker
import com.pratama.baseandroid.data.datasource.local.NewsLocalDatasource
import com.pratama.baseandroid.data.datasource.remote.NewsRemoteDatasource
import com.pratama.baseandroid.data.datasource.remote.model.toNewsList
import com.pratama.baseandroid.domain.entity.News
import com.pratama.baseandroid.utility.ThreadInfoLogger
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remote: NewsRemoteDatasource,
    private val local: NewsLocalDatasource,
    private val networkChecker: NetworkChecker
) : NewsRepository {

    override suspend fun getTopHeadlines(
        country: String,
        category: String
    ): Either<Failure, List<News>> {
        return try {
            if (networkChecker.isNetworkConnected()) {
                d { "connection : connect to internet" }
                // connected to internet
                ThreadInfoLogger.logThreadInfo("get top headlines repository")
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
