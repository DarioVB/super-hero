package com.coppel.superhero.viewModels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coppel.superhero.enums.ApiServiceStatus
import com.coppel.superhero.models.Hero
import com.coppel.superhero.network.HeroesApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HeroViewModel (app: Application) : ViewModel() {

    private val _selectedHero = MutableLiveData<Hero?>()
    val selectedHero : LiveData<Hero?>
        get () = _selectedHero

    private val _heroList = MutableLiveData<List<Hero>>()
    val heroList: LiveData<List<Hero>>
        get() = _heroList

    private val _heroStatus = MutableLiveData<ApiServiceStatus>()
    val heroStatus: LiveData<ApiServiceStatus>
        get() = _heroStatus

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        _heroStatus.value = ApiServiceStatus.NONE
    }

    fun fetchHeroesList(heroName: String?) {
        coroutineScope.launch {
            val getHeroDeferred = HeroesApi.retrofitService.getHero(heroName)

            try {
                _heroStatus.value = ApiServiceStatus.LOADING
                val queryResult = getHeroDeferred.await()
                val heroList = queryResult.results
                _heroStatus.value = ApiServiceStatus.DONE

                if(heroList.isNotEmpty()) {
                    _heroList.value = heroList
                }
            } catch (t: Throwable) {
                _heroList.value = ArrayList()
                if(t.message!! == "Unable to resolve host \"superheroapi.com\": No address associated with hostname"){
                    _heroStatus.value = ApiServiceStatus.NO_INTERNET_CONNECTION
                } else if (t.message == "The following properties were null: response_for (JSON name results-for), results (at path \$)"){
                    _heroStatus.value = ApiServiceStatus.ERROR
                }
            }
        }
    }

    fun displaySelectedHero(hero : Hero) {
        _selectedHero.value = hero
    }

    fun displaySelectedHeroComplete() {
        _selectedHero.value = null
    }
}