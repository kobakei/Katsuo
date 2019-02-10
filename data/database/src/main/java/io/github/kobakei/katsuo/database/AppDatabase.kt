package io.github.kobakei.katsuo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.kobakei.katsuo.entity.Article
import io.github.kobakei.katsuo.entity.Author

@Database(entities = [
    Article::class,
    Author::class
], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

}

fun createDb(context: Context): AppDatabase =
    Room.databaseBuilder(context, AppDatabase::class.java, "database-name").build()
