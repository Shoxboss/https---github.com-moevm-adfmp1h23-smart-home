package com.example.ai_home.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.ai_home.RoomData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomComponent(room: RoomData, selected: Boolean = false, onClicked: (id: Int) -> Unit = {}) {
    FilterChip(
        selected = selected,
        onClick = { onClicked(room.id) },
        label = { Text("${room.title}") },
        shape = RoundedCornerShape(16.dp),
    )
}