package com.coppel.superhero.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.coppel.superhero.room.*
import kotlinx.parcelize.Parcelize
import se.ansman.kotshi.JsonSerializable


@JsonSerializable
@Parcelize
@Entity(tableName = "favorite_heroes_table")
data class Hero (
    @PrimaryKey
    @ColumnInfo(name = "hero_name")
    val name :String,

    @ColumnInfo(name = "hero_power_stats")
    @TypeConverters(PowerStatsConverter::class)
    val powerstats : PowerStats,

    @ColumnInfo(name= "hero_biography")
    @TypeConverters(BiographyConverter::class)
    val biography : Biography,

    @ColumnInfo(name = "hero_appearance")
    @TypeConverters(AppearanceConverter::class)
    val appearance : Appearance,

    @ColumnInfo(name = "hero_work")
    @TypeConverters(WorkConverter::class)
    val work : Work,

    @ColumnInfo(name = "hero_connections")
    @TypeConverters(ConnectionsConverter::class)
    val connections : Connections,

    @ColumnInfo(name = "hero_image")
    @TypeConverters(ImageConverter::class)
    val image : Image
) : Parcelable