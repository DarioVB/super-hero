package com.coppel.superhero.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PowerStats(
    val intelligence: String,
    val strength: String,
    val speed: String,
    val durability: String,
    val power: String,
    val combat: String
) : Parcelable
