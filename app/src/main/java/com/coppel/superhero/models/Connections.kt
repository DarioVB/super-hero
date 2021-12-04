package com.coppel.superhero.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
@Parcelize
data class Connections (
    @Json(name = "group-affiliation")
    val groupAffiliation: String,
    val relatives: String
) : Parcelable
