package net.iessochoa.sergiocontreras.rickandmortyapi.ui.viewmodel

import net.iessochoa.sergiocontreras.rickandmortyapi.network.RickAndMortyCharacter

data class RickAndMortyUiState(
    val currentPage: Int = 1,
    val totalPages: Int = 1,
    val characters: List<RickAndMortyCharacter> = emptyList(),
    val currentState: RequestStatus = RequestStatus.Idle

)
sealed interface RequestStatus {
    object Idle : RequestStatus                       // estado inicial
    object IsLoading : RequestStatus                  // cargando
    data class Success(val characterList: List<RickAndMortyCharacter>) : RequestStatus
    data class Error(val message: String) : RequestStatus
}

sealed interface RequestStatusDetail {
    object Idle : RequestStatusDetail
    object IsLoading : RequestStatusDetail
    data class Success(val character: RickAndMortyCharacter) : RequestStatusDetail
    data class Error(val message: String) : RequestStatusDetail
}


