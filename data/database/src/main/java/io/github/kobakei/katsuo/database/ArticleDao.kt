package io.github.kobakei.katsuo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.github.kobakei.katsuo.entity.Article

@Dao
interface ArticleDao {

    @Query("SELECT * FROM article")
    suspend fun getAll(): List<Article>

    @Query("SELECT * FROM article WHERE author_id = :authorId")
    suspend fun getByAuthorId(authorId: Long): List<Article>

    @Insert
    suspend fun insertAll(vararg articles: Article)
}