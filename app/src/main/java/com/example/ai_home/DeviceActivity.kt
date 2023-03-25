package com.example.ai_home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ai_home.components.CardViewModel

class DeviceActivity : ComponentActivity() {
    private val viewModel by viewModels<CardViewModel>()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var items = remember {
                mutableListOf("1", "1", "1", "1", "1", "1", "1", "1", "1")
            }

            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Magenta)
                        .height(300.dp)
                ) {
                    Text(text = "search...")
                }
                MenuListComponent(title = "Title", items, onItemClick = {})
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuListComponent(title: String, menuItems: List<String>, onItemClick: (String) -> Unit) {

    var expanded by remember { mutableStateOf(false) }
    Card(
        shape = Shapes.None, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title, fontSize = 24.sp)
        }
    }
    SelectComponent(
        "Фильтрация",
        options = listOf("Lamp"),
        selectedOption = "all",
        onOptionSelected = {})
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        items(menuItems) { menuItem ->
            Accordion(title = menuItem, content = {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(text = "wwww")
                    Text(text = "wwww")
                    Text(text = "wwww")
                    Text(text = "wwww")
                }
            })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Accordion(title: String, content: @Composable () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 40.dp),
            onClick = { expanded = !expanded },
            shape = Shapes.None
        ) {
            Column(
                modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Box(
                        modifier = Modifier.rotate(if (expanded) 0f else -90f)
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = null
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .animateContentSize()
                .padding(start = 16.dp),
            contentAlignment = Alignment.TopStart
        ) {
            if (expanded) {
                content()
            }
        }
    }
}


@Composable
fun SelectComponent(
    label: String,
    options: List<String>,
    selectedOption: String?,
    onOptionSelected: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.ArrowDropDown
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 8.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .clickable { expanded = true }
        ) {
            Text(
                text = label,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .background(Color.LightGray, RoundedCornerShape(4.dp))
                .clickable { expanded = true }
        ) {
            Row(modifier = Modifier
                .fillMaxWidth(0.5f)
                .clickable { expanded = true }) {
                if (selectedOption != null) {
                    Text(
                        text = selectedOption,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Start
                    )
                }
                Icon(
                    imageVector = icon,
                    contentDescription = "Select icon",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {
                options.forEach { option ->
                    DropdownMenuItem(text = { Text(text = option) }, onClick = {
                        expanded = false
                        onOptionSelected(option)
                    })
                }
            }
        }
    }
}
