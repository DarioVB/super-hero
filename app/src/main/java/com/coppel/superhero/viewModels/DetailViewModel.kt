package com.coppel.superhero.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.coppel.superhero.models.Hero
import com.coppel.superhero.room.daos.FavoritesDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class DetailViewModel (
    hero: Hero,
    val app: Application) : AndroidViewModel(app) {

    private val _selectedHero = MutableLiveData<Hero>()
    val selectedHero: LiveData<Hero>
        get() = _selectedHero

    private val _statusAddToFavorite = MutableLiveData<Boolean>()
    val statusAddToFavorite: LiveData<Boolean>
        get() = _statusAddToFavorite

    init {
        _selectedHero.value = hero
    }

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
}