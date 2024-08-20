package com.example.todolist.ui.screens.mainScreens.homeScreen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolist.R
import com.example.todolist.data.Category
import com.example.todolist.ui.AppViewModelProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    val homeUiState by viewModel.homeUiState.collectAsState()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
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
    ) {
        HomeBody(
            paddingValues = it,
            viewModel = viewModel,
            categorysList = homeUiState.categoryList
        )
    }
}
@Composable
fun HomeBody(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    viewModel: HomeViewModel,
    categorysList: List<Category>
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        if (categorysList.size >= 3){
            StaticList(
                categorysList = categorysList.subList(0,4)
            )
            Spacer(modifier = modifier.height(15.dp))
            HorizontalDivider(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 10.dp),
            )
            Spacer(modifier = modifier.height(15.dp))
            if (categorysList.size > 3) {
                DynamicList(
                    categorysList = categorysList.subList(4,categorysList.size)
                )
            }
        }
    }
}
@Composable
fun StaticList(
    modifier: Modifier = Modifier,
    categorysList: List<Category>
){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(categorysList){
            StaticListItem(category = it)
        }
    }
}
@Composable
fun DynamicList(
    modifier: Modifier = Modifier,
    categorysList: List<Category>
){

}
@Composable
fun StaticListItem(
    modifier: Modifier = Modifier,
    category: Category
){
    Card(
        onClick = { /*TODO*/ },
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = modifier.width(20.dp))
            Column(
                modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
            ) {
                Icon(
                    painter = painterResource(id = category.icon),
                    contentDescription = null,
                    tint = Color(category.color),
                    modifier = modifier.size(45.dp)
                )
                Spacer(modifier = modifier.height(5.dp))
                Text(
                    text = category.title,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = category.todos.toString(),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
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
