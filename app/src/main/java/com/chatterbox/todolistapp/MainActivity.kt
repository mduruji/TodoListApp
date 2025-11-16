package com.chatterbox.todolistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoListAppTheme {
        Greeting("Android")
    }
}