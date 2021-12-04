package com.coppel.superhero.room.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.coppel.superhero.models.Hero

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(hero: Hero) : Long

    @Query("SELECT * FROM favorite_table WHERE hero_name LIKE :heroName")
    fun hero(heroName: String?) : LiveData<List<Hero>>

    @Query("DELETE FROM favorite_table")
    fun clear() : Int

    @Query("SELECT * FROM favorite_table ORDER BY hero_name ASC")
    fun favouriteHeroes() : LiveData<List<Hero>>
}