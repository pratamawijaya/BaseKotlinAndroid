package com.pratama.baseandroid.ui.homepage.rvitem

import com.pratama.baseandroid.R
import com.pratama.baseandroid.coreandroid.extensions.loadFromUrl
import com.pratama.baseandroid.domain.entity.News
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.rv_item_news.view.*

class NewsItem(private val news: News) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val title = viewHolder.itemView.newsTitle
        val thumb = viewHolder.itemView.newsThumbnail
        val source = viewHolder.itemView.newsSource

        title.text = news.title
        thumb.loadFromUrl(news.urlToImage)
        source.text = "Source : ${news.source.name}"
    }

    override fun getLayout(): Int {
        return R.layout.rv_item_news
    }

}