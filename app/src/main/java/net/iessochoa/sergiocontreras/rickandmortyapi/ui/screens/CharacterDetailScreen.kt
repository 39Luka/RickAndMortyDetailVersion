package net.iessochoa.sergiocontreras.rickandmortyapi.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.navigation.NavController
import net.iessochoa.sergiocontreras.rickandmortyapi.ui.viewmodel.RickAndMortyViewModel
import net.iessochoa.sergiocontreras.rickandmortyapi.ui.viewmodel.RequestStatusDetail


@Composable
fun CharacterDetailScreen(
    viewModel: RickAndMortyViewModel,
    characterId: Int,
    navController: NavController, // ⚡ Añadido para volver
    modifier: Modifier = Modifier
) {
    // Disparamos la carga al entrar en la pantalla
    LaunchedEffect(characterId) {
        viewModel.loadCharacterById(characterId)
    }

    //val character = viewModel.uiState.collectAsState().value.characters.find { it.id == characterId }
    val characterState = viewModel.selectedCharacter.collectAsState().value

    Scaffold(
        topBar = {
                    IconButton(onClick = { navController.popBackStack() }) { // ⚡ botón de volver
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }

        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when (characterState) {
                is RequestStatusDetail.IsLoading -> {
                    CircularProgressIndicator()
                }
                is RequestStatusDetail.Success -> {
                    val character = characterState.character
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = character.image,
                            contentDescription = character.name,
                            modifier = Modifier.size(200.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Name: ${character.name}", style = MaterialTheme.typography.titleMedium)
                        Text(text = "Species: ${character.species}", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "Status: ${character.status}", style = MaterialTheme.typography.bodyMedium)
                    }
                }
                is RequestStatusDetail.Error -> {
                    Text(text = "Error: ${characterState.message}")
                }
                RequestStatusDetail.Idle -> {
                    Text(text = "Selecciona un personaje")
                }
            }
        }
    }
}
