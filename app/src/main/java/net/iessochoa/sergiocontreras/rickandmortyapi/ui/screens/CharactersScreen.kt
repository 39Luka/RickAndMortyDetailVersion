package net.iessochoa.sergiocontreras.rickandmortyapi.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iessochoa.sergiocontreras.rickandmortyapi.ui.components.CharacterCard
import net.iessochoa.sergiocontreras.rickandmortyapi.ui.viewmodel.RickAndMortyViewModel
import net.iessochoa.sergiocontreras.rickandmortyapi.ui.viewmodel.RequestStatus
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.rickandmortyapi.ui.components.PageSelector

@Composable
fun CharactersScreen(
    modifier: Modifier = Modifier,
    viewModel: RickAndMortyViewModel = viewModel(),
    onCharacterClick: (Int) -> Unit
) {
    // Observamos el estado actual de los personajes
    val uiState = viewModel.uiState.collectAsState().value

    LaunchedEffect(uiState.characters) {
        if (uiState.characters.isEmpty()) {
            viewModel.loadCharacters(uiState.currentPage)
        }
    }


    Box(modifier = modifier.fillMaxSize()) {
        when (val state = uiState.currentState) {
            is RequestStatus.IsLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is RequestStatus.Success -> {
                Column(modifier = Modifier.fillMaxSize()) {
                    // DropDown para seleccionar la página
                    PageSelector(
                        currentPage = uiState.currentPage,
                        totalPages = uiState.totalPages,
                        onPageSelected = { page ->
                            viewModel.loadCharacters(page)
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Lista de personajes
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val characters = state.characterList

                        items(characters) { character ->
                            CharacterCard(
                                character = character,
                                onClick = { onCharacterClick(character.id) }
                            )
                        }

                    }
                }
            }
            is RequestStatus.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Error: ${state.message}")
                }
            }
            RequestStatus.Idle -> {
                // Mensaje inicial opcional
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Selecciona una página para cargar personajes")
                }
            }
        }
    }
}
