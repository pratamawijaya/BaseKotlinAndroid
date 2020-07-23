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

sealed class HomePageState {
    data class ErrorState(val message: String) : HomePageState()
    data class NewsLoadedState(val news: List<News>) : HomePageState()
}

class HomePageViewModel @Inject constructor(private val useCase: GetTopHeadlineUseCase) :
    ViewModel() {

    var homePageState = MutableLiveData<HomePageState>()

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
        homePageState.postValue(HomePageState.NewsLoadedState(list))
    }

    private fun handleFailure(failure: Failure) {
        when (failure) {
            is Failure.ServerError -> {
                e { "server error" }
                homePageState.postValue(HomePageState.ErrorState("Server Error"))
            }
            is Failure.NetworkException -> {
                homePageState.postValue(HomePageState.ErrorState("Network Exceptions"))
                e { "network exceptions" }
            }
        }
    }
}