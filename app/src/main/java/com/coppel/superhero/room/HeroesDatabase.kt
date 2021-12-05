package com.coppel.superhero.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.coppel.superhero.models.Hero
import com.coppel.superhero.room.daos.FavoritesDao
import com.coppel.superhero.utils.Constants

@Database(entities = [Hero::class], version = 1, exportSchema = false)
@TypeConverters
    (PowerStatsConverter::class,BiographyConverter::class, AppearanceConverter::class,
    WorkConverter::class, ConnectionsConverter::class, ImageConverter::class)
abstract class HeroesDatabase : RoomDatabase() {

    abstract val favoritesDao : FavoritesDao

    companion object{
        @Volatile
        private var INSTANCE : HeroesDatabase? = null

        fun getInstance(context: Context) : HeroesDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HeroesDatabase::class.java,
                        Constants.DATABASE_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}