package com.pratama.baseandroid.domain.usecase

import com.pratama.baseandroid.coreandroid.exception.Failure
import com.pratama.baseandroid.coreandroid.functional.Either
import com.pratama.baseandroid.coreandroid.usecase.UseCase
import com.pratama.baseandroid.data.repository.NewsRepository
import com.pratama.baseandroid.domain.entity.News
import javax.inject.Inject

class GetTopHeadlineUseCase @Inject constructor(private val repository: NewsRepository) :
    UseCase<List<News>, String>() {

    override suspend fun run(params: String): Either<Failure, List<News>> {
        return repository.getTopHeadlines(params)
    }

}