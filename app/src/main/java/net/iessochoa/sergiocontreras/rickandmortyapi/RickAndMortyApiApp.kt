package net.iessochoa.sergiocontreras.rickandmortyapi

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.iessochoa.sergiocontreras.rickandmortyapi.ui.navigation.RickAndMortyNavGraph

@Composable
fun RickAndMortyApp(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        // Aquí ponemos la navegación, pasando el padding para que no quede debajo del top bar si lo hubiera
        RickAndMortyNavGraph(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}
