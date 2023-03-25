package com.example.ai_home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import com.example.ai_home.components.*

data class RoomData(val id: Int, val title: String)

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<CardViewModel>()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rooms = mutableListOf(
            RoomData(0, "all")
        )

        fun addNewRoom(id: Int, title: String) {
            rooms.add(RoomData(id, title))
        }

        setContent {
            var selectedRoom by remember { mutableStateOf(rooms[0]) }
            val openDialog = remember { mutableStateOf(false) }
            val addRoom = remember { mutableStateOf(false) }
            var roomCount by remember { mutableStateOf(1) }
            val editMode = remember { mutableStateOf(false) }
            val cards by viewModel.cards.collectAsState()

            fun onAddDeviceClicked() {
                val intent = Intent(this, DeviceActivity::class.java)
                startActivity(intent)
            }

            Scaffold(bottomBar = {
                BottomAppBarComponent(
                    onAddClicked = { openDialog.value = true },
                    onEditClicked = {
                        editMode.value = !editMode.value
                    } // pass a lambda function to toggle editMode
                )
            }) {
                Column {
                    RoomListComponent(
                        onRoomChange = { id ->
                            selectedRoom = if (id == 0) {
                                rooms[0] // "all" room
                            } else {
                                rooms.find { it.id == id }
                                    ?: rooms[0] // fallback to "all" room if not found
                            }
                        },
                        rooms,
                        selectedRoom
                    )

                    val filterd = if(selectedRoom.id == 0) cards else cards.filter { card -> card.roomId == selectedRoom.id }
                    CardListComponent(
                        filterd,
                        editMode,
                        onRemoveClick = { id -> viewModel.removeCard(id) })
                }

                if (openDialog.value) {
                    AddComponent(
                        onAddDeviceClick = { onAddDeviceClicked() },
                        onAddRoomClick = {
                            addRoom.value = true
                            openDialog.value = false
                        }
                    )
                }

                if (addRoom.value) {
                    AddRoomComponent(
                        onCancelClick = { addRoom.value = false },
                        onSaveClick = { title ->
                            addNewRoom(roomCount++, title)
                            addRoom.value = false
                        }
                    )
                }
            }
        }
    }
}

