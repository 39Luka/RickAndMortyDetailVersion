package net.iessochoa.sergiocontreras.rickandmortyapi.data

import kotlinx.serialization.json.Json
import net.iessochoa.sergiocontreras.rickandmortyapi.network.RickAndMortyApiService
import net.iessochoa.sergiocontreras.rickandmortyapi.network.CharacterResponse
import net.iessochoa.sergiocontreras.rickandmortyapi.network.RickAndMortyCharacter
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object RickAndMortyRepository {

    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val api: RickAndMortyApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(RickAndMortyApiService::class.java)
    }

    // ⚡ Función para obtener lista de personajes por página
    suspend fun getCharacters(page: Int = 1): CharacterResponse {
        return api.getCharacters(page)
    }

    // ⚡ Función para obtener un personaje por ID
    suspend fun getCharacterById(id: Int): RickAndMortyCharacter {
        return api.getCharacterById(id)
    }
}
