package com.pratama.baseandroid.ui.homepage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ajalt.timberkt.d
import com.pratama.baseandroid.R
import com.pratama.baseandroid.ui.homepage.rvitem.NewsItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home_page.*
import javax.inject.Inject

@AndroidEntryPoint
class HomePageActivity : AppCompatActivity() {

    @Inject
    lateinit var homeViewModel: HomePageViewModel

    private val homeAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        setupRv()

        homeViewModel.getTopHeadlinesByCountry(
            country = "us",
            category = "business"
        )

        homeViewModel.newsLiveData.observe(this, Observer {
            d { "add news to adapter" }
            it.map { news ->
                homeAdapter.add(NewsItem(news))
            }
        })
    }

    private fun setupRv() {
        rvHomePage.layoutManager = LinearLayoutManager(this)
        rvHomePage.adapter = homeAdapter
    }
}