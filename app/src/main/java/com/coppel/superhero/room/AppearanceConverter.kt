package com.coppel.superhero.room

import androidx.room.TypeConverter
import com.coppel.superhero.models.Appearance

class AppearanceConverter {

    @TypeConverter
    fun toString(heroAppearance: Appearance?): String {
        var appearance = ""
        if (heroAppearance == null) {
            return appearance
        } else {
            appearance = "${heroAppearance.gender}," +
                    "${heroAppearance.race}," +
                    "${heroAppearance.height}," +
                    "${heroAppearance.weight}," +
                    "${heroAppearance.eyeColor}," +
                    heroAppearance.hairColor
        }
        return appearance
    }

    @TypeConverter
    fun toHeroAppearance(appearance: String?): Appearance? {
        var heroAppearance: Appearance? = Appearance(
            "", "", listOf(), listOf(), "",""
        )

        if (appearance.equals("")) {
            return heroAppearance

        } else {
            val appearanceList: List<String> = appearance!!.split(",")
            heroAppearance = Appearance(
                appearanceList[0],
                appearanceList[1],
                listOf(appearanceList[2]),
                listOf(appearanceList[3]),
                appearanceList[4],
                appearanceList[5]
            )
            return heroAppearance
        }
    }
}