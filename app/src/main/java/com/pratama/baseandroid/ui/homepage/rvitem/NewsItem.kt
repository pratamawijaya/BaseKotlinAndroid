package com.pratama.baseandroid.ui.homepage.rvitem

import android.view.View
import com.pratama.baseandroid.R
import com.pratama.baseandroid.coreandroid.extensions.loadFromUrl
import com.pratama.baseandroid.databinding.RvItemNewsBinding
import com.pratama.baseandroid.domain.entity.News
import com.xwray.groupie.viewbinding.BindableItem

class NewsItem(private val news: News) : BindableItem<RvItemNewsBinding>() {

    override fun bind(viewBinding: RvItemNewsBinding, position: Int) = with(viewBinding) {
        newsTitle.text = news.title
        newsThumbnail.loadFromUrl(news.urlToImage)
        newsSource.text = "Source ${news.source.name}"
    }

    override fun getLayout(): Int = R.layout.rv_item_news

    override fun initializeViewBinding(view: View): RvItemNewsBinding = RvItemNewsBinding.bind(view)

}