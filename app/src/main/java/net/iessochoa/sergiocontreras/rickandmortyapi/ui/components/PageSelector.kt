package net.iessochoa.sergiocontreras.rickandmortyapi.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun PageSelector(
    currentPage: Int,
    totalPages: Int,
    onPageSelected: (Int) -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Box {
        Button(onClick = { expanded = true }) {
            Text("Page $currentPage/$totalPages")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            for (page in 1..totalPages) {
                DropdownMenuItem(
                    text = { Text("Page $page") },
                    onClick = {
                        expanded = false
                        onPageSelected(page)
                    }
                )
            }
        }
    }
}


