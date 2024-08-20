package com.example.todolist.ui.components.topAppBars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(){
    CenterAlignedTopAppBar(
        title = { Text(
            text = "ToDo",
            fontWeight = FontWeight.Bold
        ) },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.Menu,
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = null
                )
            }
        }
    )
}