package com.coppel.superhero.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
@Parcelize
data class Image (
    @Json(name = "url")
    val imageUrl: String
) : Parcelable
