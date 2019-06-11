package com.pratamawijaya.basekotlin.screens.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.github.ajalt.timberkt.d
import com.pratamawijaya.basekotlin.R
import org.koin.android.ext.android.inject

class HomeActivity : AppCompatActivity() {

    private val vm: HomeVM by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        vm.homeState.observe(this, stateObserver)
        vm.getTopHeadlines()
    }

    private val stateObserver = Observer<HomeScreenState> { state ->
        when (state) {
            is LoadingState -> {

            }

            is ErrorState -> {

            }

            is ArticleLoadedState -> {
                state.articles.map {
                    d { "article loaded : ${it.title} ${it.url}" }
                }
            }

        }
    }
}
