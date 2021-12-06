package com.coppel.superhero.room.converters

import androidx.room.TypeConverter
import com.coppel.superhero.models.Biography

class BiographyConverter {

    @TypeConverter
    fun toString(heroBiography: Biography?): String {
        var biography = ""

        if (heroBiography == null) {
            return biography
        } else {
            biography = "${heroBiography.fullName}," +
                    "${heroBiography.alterEgos}," +
                    "${heroBiography.aliases}," +
                    "${heroBiography.placeOfBirth}," +
                    "${heroBiography.firstAppearance}," +
                    "${heroBiography.publisher}," +
                    heroBiography.alignment
        }
        return biography
    }

    @TypeConverter
    fun toHeroBiography(biography: String?): Biography? {
        var heroBiography: Biography? = Biography(
            "", "", listOf(), "", "","", ""
        )

        if (biography.equals("")) {
            return heroBiography

        } else {
            val biographyList: List<String> = biography!!.split(",")

            heroBiography = Biography(
                biographyList[0],
                biographyList[1],
                listOf(biographyList[2]),
                biographyList[3],
                biographyList[4],
                biographyList[5],
                biographyList[6]
            )
            return heroBiography
        }
    }
}