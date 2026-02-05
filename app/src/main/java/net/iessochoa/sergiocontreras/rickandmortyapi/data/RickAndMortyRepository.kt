package net.iessochoa.sergiocontreras.rickandmortyapi.data

import kotlinx.serialization.json.Json
import net.iessochoa.sergiocontreras.rickandmortyapi.network.RickAndMortyApiService
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object RickAndMortyRepository {

    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    private val json = Json {
        ignoreUnknownKeys = true
    }

    val api: RickAndMortyApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(RickAndMortyApiService::class.java)
    }

}
