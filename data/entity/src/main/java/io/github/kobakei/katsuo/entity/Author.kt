package io.github.kobakei.katsuo.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Author(
    val id: Long,
    val name: String,
    val image: Image
): Parcelable