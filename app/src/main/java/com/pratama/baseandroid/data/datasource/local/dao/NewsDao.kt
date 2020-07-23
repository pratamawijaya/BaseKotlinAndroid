package com.pratama.baseandroid.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pratama.baseandroid.data.datasource.local.entity.NewsEntity

@Dao
interface NewsDao {
    @Query("SELECT * FROM News")
    suspend fun getAllNews(): List<NewsEntity>

    @Insert
    suspend fun insertAll(vararg news: NewsEntity)
}