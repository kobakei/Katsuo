package io.github.kobakei.katsuo.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Article(
    val id: Long,
    val title: String,
    val body: String,
    val image: Image,
    val author: Author
): Parcelable