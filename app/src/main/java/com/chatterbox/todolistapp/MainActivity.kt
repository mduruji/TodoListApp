package com.chatterbox.todolistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chatterbox.todolistapp.ui.theme.TodoListAppTheme

/*
* todo: make display for the list
*       •make a card for each list done
*       •the cards should have a button to delete and a checkbox done
*       •make a lazy column to display the cards done
* todo: make a button to add a new list
*       •top right there should be a "+" button done
*       •this will display a pop up with a text field to add a new list
*       •the should be an add button and a cancel button
* todo: logic
*       •there should be a text list that would be mutable
*       •there should be a button to add a new item
*       •there should be a button to delete a item
*       •there should be a button that ticks an item: if item is done, the button should be ticked
* */

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoListAppTheme {
                var showDialog by remember { mutableStateOf(false) }

                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        title = { Text("Hello!") },
                        text = { Text("This is a popup dialog.") },
                        confirmButton = {
                            Button(onClick = { showDialog = false }) {
                                Text("OK")
                            }
                        }
                    )
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    "Todo List",
                                    style = MaterialTheme.typography.displaySmall,
                                    fontWeight = FontWeight.Medium
                                )
                            },
                            actions = {
                                IconButton(
                                    onClick = {
                                        showDialog = true
                                    },
                                    modifier = Modifier
                                        .size(40.dp)
                                        .background(Color.Black, CircleShape)
                                        .padding(5.dp)
                                ) {
                                    Icon(Icons.Default.Add,
                                        contentDescription = "Add", tint = Color.White)
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    ListDisplay(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ItemCard(text: String, modifier: Modifier = Modifier) {
    var done by remember { mutableStateOf(false) }
    var textValue by remember { mutableStateOf(text) }

    Card(modifier = modifier.fillMaxWidth().padding(8.dp)) {
        Row(modifier = Modifier.padding(16.dp)) {
            Box(modifier = Modifier.weight(1f)) {
                Text(text = textValue)
            }
            Box(modifier = Modifier.padding(5.dp)) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Home"
                )
            }
            Box(modifier = Modifier.padding(5.dp)) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Home"
                )
            }
        }
    }
}

@Composable
fun ListDisplay(modifier: Modifier = Modifier) {
    val todoList = remember { mutableListOf(mutableListOf<String>()) }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        content = {
            items(todoList.size) { index ->
                ItemCard(text = todoList[index].toString())
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoListAppTheme {
        ItemCard(text = "test")
    }
}