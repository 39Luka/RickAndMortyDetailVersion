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

// 📌 Definimos las rutas como sealed class
sealed class Screen(val route: String) {
    object Characters : Screen("characters")
    object CharacterDetail : Screen("character_detail/{characterId}") {
        fun createRoute(id: Int) = "character_detail/$id"
    }
}

@Composable
fun RickAndMortyNavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val viewModel: RickAndMortyViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Characters.route,
        modifier = modifier
    ) {
        // Lista de personajes
        composable(Screen.Characters.route) {
            CharactersScreen(
                modifier = Modifier.fillMaxSize(),
                viewModel = viewModel,
                onCharacterClick = { id ->
                    navController.navigate(Screen.CharacterDetail.createRoute(id))
                }
            )
        }

        // Detalle de personaje
        composable(
            route = Screen.CharacterDetail.route,
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt("characterId") ?: 0

            // Aquí usamos el enfoque híbrido para no recargar si ya está en memoria
            CharacterDetailScreen(
                modifier = Modifier.fillMaxSize(),
                viewModel = viewModel,
                characterId = characterId,
                navController = navController
            )
        }
    }
}
