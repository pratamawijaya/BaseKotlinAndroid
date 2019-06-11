package com.pratamawijaya.basekotlin.screens.home

import androidx.lifecycle.MutableLiveData
import com.github.ajalt.timberkt.d
import com.github.ajalt.timberkt.e
import com.pratamawijaya.basekotlin.data.repository.NewsRepository
import com.pratamawijaya.basekotlin.domain.Article
import com.pratamawijaya.basekotlin.screens.base.BaseViewModel
import com.pratamawijaya.basekotlin.shared.RxUtils

sealed class HomeScreenState
object LoadingState : HomeScreenState()
data class ErrorState(var msg: String) : HomeScreenState()
data class ArticleLoadedState(val articles: List<Article>) : HomeScreenState()

class HomeVM(val repo: NewsRepository) : BaseViewModel() {

    var homeState = MutableLiveData<HomeScreenState>()

    fun getTopHeadlines() {
        homeState.value = LoadingState

        repo.getArticles()
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    d { "result size ${result.size}" }
                    homeState.value = ArticleLoadedState(result)
                }, this::onError)
    }

    override fun onError(error: Throwable) {
        e { "error ${error.localizedMessage}" }
        homeState.value = ErrorState(error.localizedMessage)
    }

}