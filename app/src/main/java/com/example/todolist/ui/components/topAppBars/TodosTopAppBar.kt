package com.example.todolist.ui.components.topAppBars

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun TodosTopAppBar(
    modifier: Modifier = Modifier,
    colorSelected: Color?,
    title: String,
    icon: Int,
    imageRes: Int,
    onNavBackClicked: () -> Unit,
    onMoreClicked: () -> Unit
){
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy") // e.g., August 21, 2024
    val formattedDate = currentDate.format(formatter)
    Column {
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = onNavBackClicked,
                colors = if(colorSelected == null) IconButtonDefaults.iconButtonColors()
                else IconButtonDefaults.iconButtonColors(contentColor = colorSelected)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = null
                )
            }
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = onMoreClicked,
                    colors = if(colorSelected == null) IconButtonDefaults.iconButtonColors()
                    else IconButtonDefaults.iconButtonColors(contentColor = colorSelected)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.MoreVert,
                        contentDescription = null
                    )
                }
            }
        }
        Spacer(modifier = modifier.height(15.dp))
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            if(icon != R.drawable.icon_3d_add){
                Spacer(modifier = modifier.width(15.dp))
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = modifier.size(50.dp)
                )
            }
            Text(
                text = title,
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = modifier.padding(start = 20.dp),
                color = if(imageRes != 0) Color.Unspecified else colorSelected ?: Color.Unspecified
            )
        }
        Text(
            text = formattedDate,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            modifier = modifier.padding(start = ( 30 + if(icon != R.drawable.icon_3d_add) 65 else 0).dp),
            color = if(imageRes != 0) Color.Unspecified else colorSelected ?: Color.Unspecified
        )
    }
}