package com.pratamawijaya.basekotlin.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pratamawijaya.basekotlin.data.database.entity.ArticleEntity
import io.reactivex.Single

@Dao
interface ArticleDao{

    @Query("SELECT * from article")
    fun getAllArticles() : Single<List<ArticleEntity>>

    @Insert
    fun saveAllArticles(articles:List<ArticleEntity>)
}