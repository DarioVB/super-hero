package com.coppel.superhero.room

import androidx.room.TypeConverter
import com.coppel.superhero.models.PowerStats

class PowerStatsConverter {

    @TypeConverter
    fun toString(heroPowerStats: PowerStats?): String {
        var powerStats = ""
        if (heroPowerStats == null) {
            return powerStats
        } else {
            powerStats = "${heroPowerStats.intelligence}," +
                    "${heroPowerStats.strength}," +
                    "${heroPowerStats.speed}," +
                    "${heroPowerStats.durability}," +
                    "${heroPowerStats.power}," +
                    heroPowerStats.combat
        }
        return powerStats
    }

    @TypeConverter
    fun toHeroPowerStats(powerStats: String?): PowerStats? {
        var heroPowerStats: PowerStats? = PowerStats(
            "", "", "", "", "", ""
        )

        if (powerStats.equals("")) {
            return heroPowerStats

        } else {
            val stats: List<String> = powerStats!!.split(",")
            heroPowerStats = PowerStats(
                stats[0],
                stats[1],
                stats[2],
                stats[3],
                stats[4],
                stats[5]
            )

            return heroPowerStats
        }
    }
}