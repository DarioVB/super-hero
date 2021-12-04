package com.coppel.superhero.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import se.ansman.kotshi.JsonSerializable


@JsonSerializable
@Parcelize
data class Work(
    val occupation: String,
    val base: String
) : Parcelable
