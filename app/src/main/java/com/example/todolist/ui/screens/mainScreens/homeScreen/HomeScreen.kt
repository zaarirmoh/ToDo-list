package com.example.todolist.ui.screens.mainScreens.homeScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.todolist.R

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
){
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            StaticList()
            Spacer(modifier = modifier.height(15.dp))
            HorizontalDivider()
            Spacer(modifier = modifier.height(15.dp))
            DynamicList()
        }
    }
}
@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    icon: Int,
    text: String,
    size: Int? = null
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = modifier.width(5.dp))
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.Unspecified
        )
        Spacer(modifier = modifier.width(15.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium
        )
        if (size != null) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = size.toString()
                )
            }
        }
    }
}
@Composable
fun StaticList(
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        ListItem(
            icon = R.drawable.today_icon,
            text = "Today"
        )
        ListItem(
            icon = R.drawable.scheduled_icon,
            text = "Scheduled"
        )
        ListItem(
            icon = R.drawable.completed_icon,
            text = "Completed"
        )
        ListItem(
            icon = R.drawable.all_icon,
            text = "All"
        )
    }
}
@Composable
fun DynamicList(
    modifier: Modifier = Modifier
){

}