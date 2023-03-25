package com.example.ai_home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomAppBarComponent(onAddClicked: () -> Unit, onEditClicked: () -> Unit) {
    BottomAppBar(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            FloatingActionButton(
                onClick = { onAddClicked() },
            ) {
                Icon(Icons.Filled.Add, "Localized description")
            }
            FloatingActionButton(
                onClick = { onEditClicked() },
            ) {
                Icon(Icons.Filled.Edit, "Localized description")
            }
        }
    }
}
