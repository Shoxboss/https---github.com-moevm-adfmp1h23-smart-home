package com.example.ai_home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun AddRoomComponent(
    onCancelClick: ()-> Unit,
    onSaveClick: (title: String)->Unit
) {
    var text by rememberSaveable { mutableStateOf("") }

    Dialog(
        onDismissRequest = { /*TODO*/ }) {
        Surface(
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Новую комнату",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(24.dp))
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it.toString() },
                    singleLine = true,
                    label = { Text("Room Name") },
                    placeholder = { Text("name...") },
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = {
                        text = ""
                        onCancelClick()
                    }) {
                        Text(text = "Отменить")
                    }
                    FilledTonalButton(onClick = {
                        onSaveClick(text)
                        text = ""
                    }) {
                        Text(text = "Добавить")
                    }
                }

            }
        }
    }
}