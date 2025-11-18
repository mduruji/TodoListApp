package com.chatterbox.todolistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chatterbox.todolistapp.ui.theme.TodoListAppTheme

/*
* todo: make display for the list
*       •make a card for each list
*       •the cards should have a button to delete and a checkbox
*       •make a lazy column to display the cards
* todo: make a button to add a new list
*       •top right there should be a "+" button
*       •this will display a pop up with a text field to add a new list
*       •the should be an add button and a cancel button
* todo: logic
*       •there should be a text list that would be mutable
*       •there should be a button to add a new item
*       •there should be a button to delete a item
*       •there should be a button that ticks an item: if item is done, the button should be ticked
* */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoListAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LazyColumn(
                        contentPadding = innerPadding,
                        modifier = Modifier.fillMaxSize(),
                        content = {

                        }
                    )
                }
            }
        }
    }
}

/*
* todo: make icons for delete and done
* */
@Composable
fun ItemCard(text: String, modifier: Modifier = Modifier) {
    var done by remember { mutableStateOf(false) }
    var textValue by remember { mutableStateOf(text) }

    Card(modifier = modifier.fillMaxWidth().padding(8.dp)) {
        Row(modifier = Modifier.padding(16.dp)) {
            Box(modifier = Modifier.weight(1f)) {
                Text(text = textValue)
            }
            Box(modifier = Modifier.padding(4.dp)) {
                Text(text = "delete")
            }
            Box(modifier = Modifier.padding(4.dp)) {
                Text(text = "done")
            }
        }
    }
}

@Composable
fun ListDisplay(modifier: Modifier = Modifier) {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoListAppTheme {
        ItemCard(text = "test")
    }
}