package com.pratamawijaya.basekotlin.data.response

import com.pratamawijaya.basekotlin.data.model.ArticleModel

data class TopHeadlineResponse (val status:String,
                                val articles:List<ArticleModel>)