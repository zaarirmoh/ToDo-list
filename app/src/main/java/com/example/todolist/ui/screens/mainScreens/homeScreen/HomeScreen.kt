package com.example.todolist.ui.screens.mainScreens.homeScreen

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.todolist.ui.components.topAppBars.HomeTopAppBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onCategoryClicked: (category: Category) -> Unit = {},
    onMenuClicked: () -> Unit = {},
    onSearchClicked: () -> Unit = {}
){
    val homeUiState by viewModel.homeUiState.collectAsState()
    val openDialog = remember { mutableStateOf(false) }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { HomeTopAppBar(
            onMenuClicked = onMenuClicked,
            onSearchClicked = onSearchClicked,
        ) },
        floatingActionButton = {
            FloatingActionButton(onClick = {openDialog.value = true}) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
            }
        }
    ) {
        AddCategoryDialog(
            openDialog = openDialog,
            viewModel = viewModel,
            onCancelClicked = {  },
            onCreateListClicked = {}
        )
        HomeBody(
            paddingValues = it,
            viewModel = viewModel,
            categorysList = homeUiState.categoryList,
            onCategoryClicked = onCategoryClicked
        )
    }
}
@Composable
fun HomeBody(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    viewModel: HomeViewModel,
    categorysList: List<Category>,
    onCategoryClicked: (category: Category) -> Unit
){
    Log.d("recomposition","recomposition")
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        if (categorysList.size >= 3){
            StaticList(
                categorysList = categorysList.subList(0,4),
                onCategoryClicked = onCategoryClicked
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
                    categorysList = categorysList.subList(4,categorysList.size),
                    onCategoryClicked = onCategoryClicked
                )
            }
        }
    }
}
@Composable
fun StaticList(
    modifier: Modifier = Modifier,
    categorysList: List<Category>,
    onCategoryClicked: (category: Category) -> Unit
){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(10.dp),
        modifier = modifier
    ) {
        items(categorysList){
            StaticListItem(
                category = it,
                onCategoryClicked = onCategoryClicked
            )
        }
    }
}
@Composable
fun DynamicList(
    modifier: Modifier = Modifier,
    categorysList: List<Category>,
    onCategoryClicked: (category: Category) -> Unit
){
    LazyColumn {
        items(categorysList){
            DynamicListItem(
                category = it,
                onCategoryClicked = onCategoryClicked
            )
        }
    }
}
@Composable
fun StaticListItem(
    modifier: Modifier = Modifier,
    category: Category,
    onCategoryClicked: (category: Category) -> Unit,
){
    Card(
        onClick = { onCategoryClicked(category) },
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
                Image(
                    painter = painterResource(id = category.icon),
                    contentDescription = null,
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
fun DynamicListItem(
    modifier: Modifier = Modifier,
    category: Category,
    onCategoryClicked: (category: Category) -> Unit,
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onCategoryClicked(category) }
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = modifier.width(5.dp))
        if(category.icon == R.drawable.icon_3d_add){
            Icon(
                imageVector = Icons.Rounded.Menu,
                contentDescription = null,
                tint = Color(category.color),
                modifier = modifier.size(30.dp)
            )
        }else{
            Image(
                painter = painterResource(id = category.icon),
                contentDescription = null,
                modifier = modifier.size(30.dp)
            )
        }
        Spacer(modifier = modifier.width(15.dp))
        Text(
            text = category.title,
            style = MaterialTheme.typography.titleMedium
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(end = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = category.todos.toString()
            )
        }
    }
}
