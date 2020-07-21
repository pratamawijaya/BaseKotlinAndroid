package com.pratama.baseandroid.ui.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ajalt.timberkt.d
import com.github.ajalt.timberkt.e
import com.pratama.baseandroid.coreandroid.exception.Failure
import com.pratama.baseandroid.domain.entity.News
import com.pratama.baseandroid.domain.usecase.GetTopHeadlineUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomePageViewModel @Inject constructor(private val useCase: GetTopHeadlineUseCase) :
    ViewModel() {

    fun getTopHeadlinesByCountry(country: String, category: String) {
        viewModelScope.launch {
            useCase.run(
                GetTopHeadlineUseCase.TopHeadlineParam(
                    country = country,
                    category = category
                )
            )
                .fold(::handleFailure, ::handleTopHeadlines)
        }
    }

    private fun handleTopHeadlines(list: List<News>) {
        list.map {
            d { "news ${it.title} - ${it.url}" }
        }
    }

    private fun handleFailure(failure: Failure) {
        when (failure) {
            is Failure.ServerError -> {
                e { "server error" }
            }
            is Failure.NetworkException -> {
                e { "network exceptions" }
            }
        }
    }
}