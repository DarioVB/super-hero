package com.coppel.superhero.room

import androidx.room.TypeConverter
import com.coppel.superhero.models.Work

class WorkConverter {

    @TypeConverter
    fun toString(heroWork: Work?): String {
        var work = ""
        if (heroWork == null) {
            return work
        } else {
            work = "${heroWork.occupation}," +
                    heroWork.base
        }
        return work
    }

    @TypeConverter
    fun toHeroWork(work: String?): Work? {
        var heroWork: Work? = Work(
            "", ""
        )
        if (work.equals("")) {
            return heroWork

        } else {
            val workList: List<String> = work!!.split(",")
            heroWork = Work(
                workList[0],
                workList[1]
            )
            return heroWork
        }
    }
}