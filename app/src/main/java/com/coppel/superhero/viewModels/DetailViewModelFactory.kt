package com.coppel.superhero.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coppel.superhero.models.Hero
import com.coppel.superhero.room.daos.FavoritesDao

class DetailViewModelFactory (

    private val superHero: Hero,
    private val application: Application

) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(
                superHero,
                application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}