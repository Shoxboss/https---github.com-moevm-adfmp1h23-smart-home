package com.example.ai_home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ai_home.RoomData

@Composable
fun RoomListComponent(
    onRoomChange: (id: Int) -> Unit,
    rooms: List<RoomData>,
    selectedRoom: RoomData
) {
    var current by remember { mutableStateOf(0) }

    fun roomChange(id: Int) {
        onRoomChange(id)
        current = id
    }

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(4.dp)
    ) {
        items(rooms) { room ->
            RoomComponent(room, onClicked = { id -> roomChange(id) }, selected = current == room.id)
        }
    }
}
