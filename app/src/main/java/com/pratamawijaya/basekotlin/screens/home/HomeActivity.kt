package com.pratamawijaya.basekotlin.screens.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ajalt.timberkt.d
import com.pratamawijaya.basekotlin.R
import com.pratamawijaya.basekotlin.shared.view.ArticleItemView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject

class HomeActivity : AppCompatActivity() {

    private val homeAdapter = GroupAdapter<ViewHolder>()

    private val vm: HomeVM by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        rvHome.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = homeAdapter
        }

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
                    homeAdapter.add(ArticleItemView(it))
                }
            }

        }
    }
}
