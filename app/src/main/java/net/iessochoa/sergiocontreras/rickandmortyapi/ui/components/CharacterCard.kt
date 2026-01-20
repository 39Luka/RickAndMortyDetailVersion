package net.iessochoa.sergiocontreras.rickandmortyapi.ui.components

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CharacterCard(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Text(text = "Character")

    }


}

@Preview(showBackground = true)
@Composable
fun CharacterCardPreview() {
    CharacterCard()
}