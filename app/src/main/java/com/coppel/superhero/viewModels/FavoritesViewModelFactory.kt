package com.coppel.superhero.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coppel.superhero.room.daos.FavoritesDao


class FavoritesViewModelFactory (
    private val application: Application,
    private val database: FavoritesDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            return FavoritesViewModel(
                application,
                database
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}