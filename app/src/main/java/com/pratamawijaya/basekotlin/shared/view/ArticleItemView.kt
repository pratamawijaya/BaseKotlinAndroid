package com.pratamawijaya.basekotlin.shared.view

import com.pratamawijaya.basekotlin.R
import com.pratamawijaya.basekotlin.domain.Article
import com.pratamawijaya.basekotlin.shared.extensions.loadSrc
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.layout_item_article.view.*

class ArticleItemView(private val article: Article) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        val imgArticle = viewHolder.itemView.imgArticle
        val titleArticle = viewHolder.itemView.titleArticle

        imgArticle.loadSrc(article.imageUrl)
        titleArticle.text = article.title
    }

    override fun getLayout(): Int = R.layout.layout_item_article
}