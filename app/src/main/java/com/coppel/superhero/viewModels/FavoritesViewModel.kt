package com.coppel.superhero.viewModels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coppel.superhero.models.Hero
import com.coppel.superhero.room.daos.FavoritesDao
import kotlinx.coroutines.*


class FavoritesViewModel(val app: Application, val database: FavoritesDao) : ViewModel() {

    private val _selectedHero = MutableLiveData<Hero>()
    val selectedHero: LiveData<Hero>
        get() = _selectedHero

    private val _deletedHeroes = MutableLiveData<Boolean>()
    val deletedHeroes: LiveData<Boolean>
        get() = _deletedHeroes

    private val _favouriteHeroes = MutableLiveData<LiveData<List<Hero>>>()
    val favouriteHero: LiveData<List<Hero>>
        get() = _favouriteHeroes.value!!

    val viewModelJob = Job()
    val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        loadFavourites()
    }

    private fun loadFavourites() {
        _favouriteHeroes.value = database.favoriteHeroes()
    }

    fun displaySelectedHero(favouriteHero: Hero) {
        _selectedHero.value = favouriteHero
    }

    fun displaySelectedHeroComplete() {
        _selectedHero.value = null
    }

    fun clearFavourites() {
        coroutineScope.launch {
            val clearedRows = clearDatabase()
            if (clearedRows > 0) _deletedHeroes.value = true
        }
    }

    private suspend fun clearDatabase(): Int {
        var rowCleared = 0
        withContext(Dispatchers.IO) {
            rowCleared = database.delete()
        }
        return rowCleared
    }

    fun searchHero(query: String?) {
        val appendedQuery = "%${query}%"
        _favouriteHeroes.value = database.getHero(appendedQuery)
    }
}