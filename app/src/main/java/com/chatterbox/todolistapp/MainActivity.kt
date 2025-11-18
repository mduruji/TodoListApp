package com.chatterbox.todolistapp

import android.R.attr.text
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
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


class TodoList : ViewModel() {
    val items = mutableStateListOf<String>()

    fun addItem(item: String) {
        if (item.isNotBlank() && !items.contains(item)) {
            items.add(item)
        }
    }

    fun removeItem(item: String) {
        items.remove(item)
    }
}
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoListAppTheme {
                var textInput by remember { mutableStateOf("") }
                var showDialog by remember { mutableStateOf(false) }
                val todoListViewModel = remember { TodoList() }

                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = {
                            showDialog = false
                            textInput = ""
                        },
                        title = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "Add New Item")

                                IconButton(onClick = {
                                    showDialog = false
                                    textInput = ""
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Close"
                                    )
                                }
                            }
                        },
                        text = {
                            Text("Enter your text:")
                            Spacer(modifier = Modifier.height(8.dp))

                            TextField(
                                value = textInput,
                                onValueChange = {
                                    textInput = it
                                },
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        confirmButton = {
                            Button(onClick = {
                                showDialog = false
                                todoListViewModel.addItem(textInput)
                                textInput = ""
                            }) {
                                Text("Add")
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
                    ListDisplay(
                        modifier = Modifier.padding(innerPadding),
                        todoList = todoListViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun ItemCard(
    text: String,
    modifier: Modifier = Modifier,
    onDelete: () -> Unit
) {
    var textValue by remember { mutableStateOf(text) }

    Card(modifier = modifier.fillMaxWidth().padding(8.dp)) {
        Row(modifier = Modifier.padding(16.dp)) {
            Box(modifier = Modifier.weight(1f)) {
                Text(text = textValue)
            }
            IconButton(
                modifier = Modifier.padding(5.dp),
                onClick = {
                    onDelete()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Home"
                )
            }
            IconButton(
                modifier = Modifier.padding(5.dp),
                onClick = {
                    onDelete()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Home"
                )
            }
        }
    }
}

@Composable
fun ListDisplay(
    modifier: Modifier = Modifier,
    todoList: TodoList
) {

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        content = {
            if (todoList.items.isEmpty()) {
                item {
                    Text("Add a something to the list")
                }
            } else {
                items(
                    todoList.items,
                    key = { item -> item }
                ) { item ->
                    ItemCard(
                        text = item,
                        onDelete = {
                            todoList.removeItem(item)
                        }
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoListAppTheme {
        ListDisplay(todoList = TodoList())
    }
}