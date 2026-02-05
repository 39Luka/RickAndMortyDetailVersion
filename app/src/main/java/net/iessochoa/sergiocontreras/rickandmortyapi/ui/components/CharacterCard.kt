package net.iessochoa.sergiocontreras.rickandmortyapi.ui.components

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import net.iessochoa.sergiocontreras.rickandmortyapi.network.RickAndMortyCharacter

@Composable
fun CharacterCard(
    character: RickAndMortyCharacter,
    modifier: Modifier = Modifier,
    onClick: () -> Unit

) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen del personaje
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.LightGray, CircleShape)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Texto y estado
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = character.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Punto de color según estado
                    val statusColor = when (character.status.lowercase()) {
                        "alive" -> Color.Green
                        "dead" -> Color.Red
                        else -> Color.Gray
                    }

                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(statusColor, CircleShape)
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = "${character.status} - ${character.species}",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}
