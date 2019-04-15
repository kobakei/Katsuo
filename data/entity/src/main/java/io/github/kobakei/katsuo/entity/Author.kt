package io.github.kobakei.katsuo.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Author(
    @ColumnInfo(name = "author_id") val id: Long,
    val name: String,
    @Embedded val image: AuthorImage?
): Parcelable

@Parcelize
@Serializable
data class AuthorImage(
    @ColumnInfo(name = "author_image_thumbnail") val thumbnail: String,
    @ColumnInfo(name = "author_image_large") val large: String
): Parcelable