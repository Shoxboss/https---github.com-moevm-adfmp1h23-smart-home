package com.example.ai_home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceCardComponent(
    card: CardData,
    editMode:  MutableState<Boolean>,
    onRemoveClick: (id: Int) -> Unit // callback for remove button click
) {
    var isFlipped by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 200.dp)
            .padding(4.dp)
            .graphicsLayer {
                this.transformOrigin = TransformOrigin.Center
            },
        onClick = { isFlipped = !isFlipped },
        shape = RoundedCornerShape(16.dp),
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(8.dp)
            ) {
                Text(text = card.title, fontWeight = FontWeight.Bold)
                Text(text = card.description)
            }

            // Add remove button to top right corner
            if (editMode.value) {
                IconButton(
                    onClick = { onRemoveClick(card.id) },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Remove")
                }
            }
        }
    }
}
