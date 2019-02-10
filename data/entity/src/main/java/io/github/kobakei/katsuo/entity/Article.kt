package io.github.kobakei.katsuo.entity

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Entity(indices = [Index("author_id")])
@Parcelize
@Serializable
data class Article(
    @PrimaryKey val id: Long,
    val title: String,
    val body: String,
    @Embedded val image: ArticleImage,
    @Embedded val author: Author
): Parcelable

@Parcelize
@Serializable
data class ArticleImage(
    @ColumnInfo(name = "article_image_thumbnail") val thumbnail: String,
    @ColumnInfo(name = "article_image_large") val large: String
): Parcelable