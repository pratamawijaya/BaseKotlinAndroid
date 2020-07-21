package com.pratama.baseandroid.data.repository

import com.pratama.baseandroid.coreandroid.exception.Failure
import com.pratama.baseandroid.coreandroid.functional.Either
import com.pratama.baseandroid.coreandroid.network.NetworkChecker
import com.pratama.baseandroid.data.datasource.local.NewsLocalDatasource
import com.pratama.baseandroid.data.datasource.remote.NewsRemoteDatasource
import com.pratama.baseandroid.domain.entity.News
import java.lang.Exception
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remote: NewsRemoteDatasource,
    private val local: NewsLocalDatasource,
    private val networkChecker: NetworkChecker
) : NewsRepository {

    override suspend fun getTopHeadlines(country: String): Either<Failure, List<News>> {
        //todo: implementasi ke remote dan local server
        return try {
            if (networkChecker.isNetworkConnected()) {
                // connected to internet
                val response = remote.getTopHeadlines(category = "", country = country)
            } else {
                // not connected
            }
            Either.Right(emptyList())
        } catch (ex: Exception) {
            Either.Left(Failure.ServerError)
        }
    }
}