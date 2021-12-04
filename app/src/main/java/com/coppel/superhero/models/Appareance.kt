package com.coppel.superhero.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
@Parcelize
data class Appearance (
    val gender: String,
    val race: String,
    val height: List<String>,
    val weight: List<String>,
    @Json(name = "eye-color")
    val eyeColor : String,
    @Json(name = "hair-color")
    val hairColor: String
) : Parcelable
