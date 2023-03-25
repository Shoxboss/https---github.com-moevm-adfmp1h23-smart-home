package com.example.ai_home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog


@Composable
fun AddComponent(
    onAddDeviceClick: ()->Unit,
    onAddRoomClick: ()->Unit  ,
) {

    Dialog(onDismissRequest = {}) {
        Surface(
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp)) {
                TextButton(
                    onClick = {
                        onAddRoomClick()
                    }, modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Добавить комнату")
                }
                TextButton(
                    onClick = { onAddDeviceClick() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Добавить устройство")
                }
            }
        }
    }
}