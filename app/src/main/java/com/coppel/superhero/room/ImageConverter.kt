package com.coppel.superhero.room

import androidx.room.TypeConverter
import com.coppel.superhero.models.Image

class ImageConverter {

    @TypeConverter
    fun toString(heroImage: Image?): String {
        var image = ""
        if (heroImage == null) {
            return image
        } else {
            image = heroImage.imageUrl
        }
        return image
    }

    @TypeConverter
    fun toImage(image: String?): Image? {
        var heroImage: Image? = Image(
            ""
        )

        if (image.equals("")) {
            return heroImage

        } else {
            val imageList: List<String> = image!!.split(",")
            heroImage = Image(
                imageList[0]
            )
            return heroImage
        }
    }
}