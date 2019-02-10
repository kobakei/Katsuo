package io.github.kobakei.katsuo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.kobakei.katsuo.entity.Article

@Database(entities = [
    Article::class
], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

}

fun createDb(context: Context): AppDatabase =
    Room.databaseBuilder(context, AppDatabase::class.java, "database-name").build()
