package com.pratama.baseandroid.data.repository

import com.pratama.baseandroid.coreandroid.exception.Failure
import com.pratama.baseandroid.coreandroid.functional.Either
import com.pratama.baseandroid.data.datasource.remote.NewsRemoteDatasource
import com.pratama.baseandroid.domain.entity.News

class NewsRepositoryImpl(private val remote: NewsRemoteDatasource) : NewsRepository {

    override fun getTopHeadlines(country: String): Either<Failure, List<News>> {
        TODO("Not yet implemented")
    }
}