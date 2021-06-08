package com.pratama.baseandroid.ui.homepage

import androidx.lifecycle.viewModelScope
import com.pratama.baseandroid.coreandroid.BaseViewModel
import com.pratama.baseandroid.coreandroid.exception.Failure
import com.pratama.baseandroid.domain.entity.News
import com.pratama.baseandroid.domain.usecase.GetTopHeadlineUseCase
import com.pratama.baseandroid.utility.ThreadInfoLogger
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListNewsViewModel @Inject constructor(private val getTopHeadlineUseCase: GetTopHeadlineUseCase) :
    BaseViewModel<ListNewsViewModel.ListNewsState>() {

    sealed class ListNewsState {
        object Loading : ListNewsState()
        data class NewsLoaded(val news: List<News>) : ListNewsState()
        data class Error(val message: String) : ListNewsState()
    }

    fun getTopHeadlinesByCountry(country: String, category: String) {
        viewModelScope.launch {
            uiState.postValue(ListNewsState.Loading)

            ThreadInfoLogger.logThreadInfo("get top headlines viewmodel")
            val result =
                getTopHeadlineUseCase.run(GetTopHeadlineUseCase.TopHeadlineParam(country, category))

            result.fold({ failure ->
                when (failure) {
                    is Failure.ServerError -> {
                        uiState.postValue(ListNewsState.Error("Server Error"))
                    }
                    else -> uiState.postValue(ListNewsState.Error("Unknown Error"))
                }

            }, { result ->
                if (!result.isNullOrEmpty()) {
                    uiState.postValue(ListNewsState.NewsLoaded(result))
                }
            })
        }
    }

}