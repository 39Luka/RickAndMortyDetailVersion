package net.iessochoa.sergiocontreras.rickandmortyapi.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import net.iessochoa.sergiocontreras.rickandmortyapi.ui.screens.CharactersScreen
import net.iessochoa.sergiocontreras.rickandmortyapi.ui.screens.CharacterDetailScreen
import net.iessochoa.sergiocontreras.rickandmortyapi.ui.viewmodel.RickAndMortyViewModel

@Composable
fun RickAndMortyNavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val viewModel: RickAndMortyViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "characters",
        modifier = modifier
    ) {
        composable("characters") {
            CharactersScreen(
                modifier = Modifier.fillMaxSize(),
                viewModel = viewModel,
                onCharacterClick = { id ->
                    navController.navigate("character_detail/$id")
                }
            )
        }
        composable(
            route = "character_detail/{characterId}",
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt("characterId") ?: 0
            CharacterDetailScreen(
                modifier = Modifier.fillMaxSize(),
                viewModel = viewModel,
                characterId = characterId,
                navController = navController // ⚡ aquí
            )
        }

    }
}
