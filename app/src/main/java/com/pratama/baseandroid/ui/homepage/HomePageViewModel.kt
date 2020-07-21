package com.pratama.baseandroid.ui.homepage

import androidx.lifecycle.MutableLiveData
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

    var newsLiveData = MutableLiveData<List<News>>()

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
//        list.map {
//            d { "news ${it.title} - ${it.url}" }
//            newsLiveData.postValue(it)
//        }
        newsLiveData.postValue(list)
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