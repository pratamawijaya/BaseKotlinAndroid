package com.pratama.baseandroid.data.repository

import com.pratama.baseandroid.coreandroid.exception.Failure
import com.pratama.baseandroid.coreandroid.functional.Either
import com.pratama.baseandroid.domain.entity.News

interface NewsRepository {
    fun getTopHeadlines(country: String): Either<Failure, List<News>>
}