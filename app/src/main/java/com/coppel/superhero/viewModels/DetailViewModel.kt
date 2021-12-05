package com.coppel.superhero.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.coppel.superhero.models.Hero
import com.coppel.superhero.room.daos.FavoritesDao
import kotlinx.coroutines.*

class DetailViewModel (
    hero: Hero,
    val app: Application,
    val dbFavorites: FavoritesDao) : AndroidViewModel(app) {

    private val _selectedHero = MutableLiveData<Hero>()
    val selectedHero: LiveData<Hero>
        get() = _selectedHero

    private val _statusAddToFavorite = MutableLiveData<Boolean>()
    val statusAddToFavorite: LiveData<Boolean>
        get() = _statusAddToFavorite

    private val _isHeroAdded = MutableLiveData<Boolean>()
    val isHeroAdded: LiveData<Boolean>
        get() = _isHeroAdded

    init {
        _selectedHero.value = hero
    }

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    override fun onCleared() {
        viewModelJob.cancel()
    }

    fun isHeroAdded() {
        coroutineScope.launch {
            val hero = _selectedHero.value
            val isAdded = checkRecord(hero!!)
            _isHeroAdded.value = isAdded
        }
    }

    fun addToFavourites() {
        coroutineScope.launch {
            val hero = _selectedHero.value
            val status = saveFavoriteHero(hero!!)
            _statusAddToFavorite.value = status > 0
        }
    }

    private suspend fun saveFavoriteHero(hero: Hero): Long {
        var insertId = 0L
        withContext(Dispatchers.IO) {
            insertId = dbFavorites.insert(hero)
        }
        return insertId
    }

    private suspend fun checkRecord(hero: Hero): Boolean {
        var isExist = false
        withContext(Dispatchers.IO) {
            isExist = dbFavorites.isRowExist(hero.id)
        }
        Log.e(javaClass.simpleName, "---exist---> $isExist")
        return isExist
    }
}