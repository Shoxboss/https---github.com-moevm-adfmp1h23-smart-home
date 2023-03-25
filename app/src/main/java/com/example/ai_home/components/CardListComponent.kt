package com.example.ai_home.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class CardData(val id: Int, val roomId: Int, val title: String, val description: String)

@Composable
fun CardListComponent(cards: List<CardData>, editMode: MutableState<Boolean>, onRemoveClick: (id: Int) -> Unit ) {
    val updatedCards = rememberUpdatedState(cards)

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(18.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = Modifier.padding(8.dp, bottom = 100.dp),
    ) {
        items(updatedCards.value) { card ->
            DeviceCardComponent(
                card = card,
                editMode = editMode,
                onRemoveClick = onRemoveClick
            )
        }
    }
}
