package com.example.todolist.ui.components.topAppBars

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TodosTopAppBar(
    modifier: Modifier = Modifier,
    onNavBackClicked: () -> Unit,
){
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        IconButton(onClick = onNavBackClicked) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                contentDescription = null
            )
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.MoreVert,
                    contentDescription = null
                )
            }
        }
    }
}