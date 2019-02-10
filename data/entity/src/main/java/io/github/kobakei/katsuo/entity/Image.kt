package io.github.kobakei.katsuo.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Image(
    val thumbnail: String,
    val large: String
): Parcelable