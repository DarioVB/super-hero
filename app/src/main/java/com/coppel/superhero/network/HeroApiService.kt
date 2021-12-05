package com.coppel.superhero.network

import com.coppel.superhero.models.HeroResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

private const val ACCESS_TOKEN = "10220016060375240"
private const val BASE_URL = "https://superheroapi.com/api/$ACCESS_TOKEN/"


private val moshi = Moshi.Builder()
    .add(ApplicationJsonAdapterFactory)
    .build()

private val interceptor = HttpLoggingInterceptor()
    .setLevel(HttpLoggingInterceptor.Level.BODY)

private val client = OkHttpClient.Builder()
    .addInterceptor(interceptor)
    .readTimeout(30, TimeUnit.SECONDS)
    .connectTimeout(30, TimeUnit.SECONDS)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface HeroesApiService {
    @GET("search/{heroName}")
    fun getHero(@Path("heroName")heroName: String?): Deferred<HeroResponse>
}

object HeroesApi {
    val retrofitService: HeroesApiService by lazy {
        retrofit.create(HeroesApiService::class.java)
    }
}