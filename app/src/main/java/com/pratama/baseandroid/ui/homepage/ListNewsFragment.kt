package com.pratama.baseandroid.ui.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ajalt.timberkt.d
import com.pratama.baseandroid.coreandroid.base.BaseFragmentBinding
import com.pratama.baseandroid.coreandroid.extensions.toGone
import com.pratama.baseandroid.coreandroid.extensions.toVisible
import com.pratama.baseandroid.databinding.FragmentListNewsBinding
import com.pratama.baseandroid.ui.homepage.rvitem.NewsItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListNewsFragment : BaseFragmentBinding<FragmentListNewsBinding>() {

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListNewsFragment().apply {
            }
    }

    @Inject
    lateinit var listNewsViewModel: ListNewsViewModel

    private val listNewsAdapter = GroupAdapter<GroupieViewHolder>()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentListNewsBinding =
        FragmentListNewsBinding::inflate

    override fun setupView(binding: FragmentListNewsBinding) = with(binding) {
        // setupRecyclerview
        rvListNews.layoutManager = LinearLayoutManager(requireActivity())
        rvListNews.adapter = listNewsAdapter

        swipeRefreshLayout.setOnRefreshListener {
            listNewsAdapter.clear()

            callData()
        }

        callData()

        listNewsViewModel.uiState().observe(viewLifecycleOwner, { state ->
            when (state) {

                is ListNewsViewModel.ListNewsState.Loading -> {
                    loadingIndicator.toVisible()
                }

                is ListNewsViewModel.ListNewsState.NewsLoaded -> {
                    loadingIndicator.toGone()
                    swipeRefreshLayout.isRefreshing = false


                    state.news.map {
                        d { "news loaded -> ${it.title}" }
                        listNewsAdapter.add(NewsItem(it))
                    }
                }
            }
        })
    }

    private fun callData() {
        listNewsViewModel.getTopHeadlinesByCountry(country = "us", category = "technology")
    }

}