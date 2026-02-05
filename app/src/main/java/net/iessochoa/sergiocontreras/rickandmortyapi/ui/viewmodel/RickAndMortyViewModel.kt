package net.iessochoa.sergiocontreras.rickandmortyapi.ui.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import net.iessochoa.sergiocontreras.rickandmortyapi.data.RickAndMortyRepository

class RickAndMortyViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(RickAndMortyUiState())
    val uiState: StateFlow<RickAndMortyUiState> = _uiState.asStateFlow()

    private val _selectedCharacter = MutableStateFlow<RequestStatusDetail>(RequestStatusDetail.Idle)
    val selectedCharacter: StateFlow<RequestStatusDetail> = _selectedCharacter.asStateFlow()


    init {
        loadCharacters()
    }

    fun loadCharacters(page: Int = 1) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                currentState = RequestStatus.IsLoading
            )
            try {
                val response = RickAndMortyRepository.getCharacters(page) // 🔹 necesitas que la API acepte page
                _uiState.value = _uiState.value.copy(
                    characters = response.results,
                    currentState = RequestStatus.Success(response.results),
                    currentPage = page,
                    totalPages = response.info.pages
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    currentState = RequestStatus.Error(e.message ?: "Error desconocido")
                )
            }
        }
    }



    fun loadCharacterById(id: Int){
        viewModelScope.launch {
            _selectedCharacter.value = RequestStatusDetail.IsLoading
            try {
                val character = RickAndMortyRepository.getCharacterById(id)
                _selectedCharacter.value = RequestStatusDetail.Success(character)
            } catch (e: Exception) {
                _selectedCharacter.value = RequestStatusDetail.Error(e.message ?: "Error desconocido")
            }
        }
    }
}