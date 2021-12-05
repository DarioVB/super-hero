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
@Entity(tableName = "favorites_table")
data class Hero (
    @PrimaryKey
    @ColumnInfo(name = "hero_id")
    var id: String,

    @ColumnInfo(name = "hero_name")
    var name: String,

    @ColumnInfo(name = "hero_power_stats")
    @TypeConverters(PowerStatsConverter::class)
    var powerstats: PowerStats,

    @ColumnInfo(name= "hero_biography")
    @TypeConverters(BiographyConverter::class)
    var biography: Biography,

    @ColumnInfo(name = "hero_appearance")
    @TypeConverters(AppearanceConverter::class)
    var appearance: Appearance,

    @ColumnInfo(name = "hero_work")
    @TypeConverters(WorkConverter::class)
    var work: Work,

    @ColumnInfo(name = "hero_connections")
    @TypeConverters(ConnectionsConverter::class)
    var connections: Connections,

    @ColumnInfo(name = "hero_image")
    @TypeConverters(ImageConverter::class)
    var image: Image
) : Parcelable