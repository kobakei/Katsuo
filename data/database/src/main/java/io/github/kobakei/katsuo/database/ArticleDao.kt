package io.github.kobakei.katsuo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.github.kobakei.katsuo.entity.Article

@Dao
interface ArticleDao {

    @Query("SELECT * FROM article")
    fun getAll(): List<Article>

    @Insert
    fun insertAll(vararg articles: Article)
}