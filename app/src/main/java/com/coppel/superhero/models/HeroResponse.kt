package com.coppel.superhero.models

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable


@JsonSerializable
data class HeroResponse (
    val response: String,
    @Json(name = "results-for")
    val responseFor: String,
    val results : List<Hero>
)