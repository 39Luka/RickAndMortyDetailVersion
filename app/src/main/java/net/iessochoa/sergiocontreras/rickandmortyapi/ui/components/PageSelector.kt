package net.iessochoa.sergiocontreras.rickandmortyapi.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun PageSelector(
    currentPage: Int,
    totalPages: Int,
    onPageSelected: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(onClick = { expanded = true }) {
            Text("Página $currentPage / $totalPages")
        }

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            for (i in 1..totalPages) {
                DropdownMenuItem(
                    text = { Text("Página $i") },
                    onClick = {
                        onPageSelected(i)
                        expanded = false
                    }
                )
            }
        }
    }
}
