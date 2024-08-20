package com.example.todolist.ui.screens.mainScreens.listDetailsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.ui.components.topAppBars.TodosTopAppBar

@Composable
fun TodosScreen(
    modifier: Modifier = Modifier,
    imageRes: Int,
    title: String,
    onNavBackClicked: () -> Unit
){
    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Box {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = modifier.fillMaxSize(),
                contentScale = ContentScale.FillHeight
            )
            Column(
                modifier = modifier.fillMaxSize()
            ) {
                Spacer(modifier = modifier.height(30.dp))
                TodosTopAppBar(
                    onNavBackClicked = onNavBackClicked
                )
                Spacer(modifier = modifier.height(15.dp))
                Text(
                    text = title,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = modifier.padding(start = 20.dp)
                )
            }
        }
    }
}