package net.iessochoa.sergiocontreras.rickandmortyapi.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    @SerialName("info")val info: RickAndMortyInfo,
    val results: List<RickAndMortyCharacter>

)

@Serializable
data class RickAndMortyInfo(
    val count: Int,
    val pages: Int,
    val next: String? = null,
    val prev: String? = null

)

@Serializable
data class RickAndMortyCharacter(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val image: String
)