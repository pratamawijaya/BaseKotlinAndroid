package com.pratamawijaya.basekotlin.screens.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ajalt.timberkt.d
import com.pratamawijaya.basekotlin.R
import com.pratamawijaya.basekotlin.shared.PaginationScrollListener
import com.pratamawijaya.basekotlin.shared.view.ArticleItemView
import com.pratamawijaya.basekotlin.shared.view.LoadmoreItemView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject

class HomeActivity : AppCompatActivity() {

    private val homeAdapter = GroupAdapter<ViewHolder>()

    private val vm: HomeVM by inject()

    private var page = 1
    private var isLoadMore = false
    private var isLastPage = false

    private var loadmoreItemView = LoadmoreItemView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val linearLayoutManager = LinearLayoutManager(this)

        rvHome.apply {
            layoutManager = linearLayoutManager
            adapter = homeAdapter
        }

        var query = "Saham"

        vm.homeState.observe(this, stateObserver)
        vm.getEverything(query = query, page = page)

        rvHome.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return this@HomeActivity.isLoadMore
            }

            override fun loadMoreItems() {
                isLoadMore = true
                page++

                d { "loadmore page $page" }

                vm.getEverything(query, page)
            }
        })
    }

    private val stateObserver = Observer<HomeScreenState> { state ->
        when (state) {
            is LoadingState -> {
                if (isLoadMore) {
                    // add loading indicator
                    homeAdapter.add(loadmoreItemView)
                } else {
                }
            }
            is ErrorState -> {
            }
            is ArticleLoadedState -> {
                if (isLoadMore) {
                    // remove loading indicator
                    homeAdapter.remove(loadmoreItemView)
                    isLoadMore = false
                }

                state.articles.map {
                    homeAdapter.add(ArticleItemView(it))
                }
            }
        }
    }
}
