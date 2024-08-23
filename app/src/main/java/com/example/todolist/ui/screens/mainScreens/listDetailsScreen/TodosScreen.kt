package com.example.todolist.ui.screens.mainScreens.listDetailsScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolist.R
import com.example.todolist.data.Item
import com.example.todolist.ui.AppViewModelProvider
import com.example.todolist.ui.components.topAppBars.TodosTopAppBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun TodosScreen(
    modifier: Modifier = Modifier,
    viewModel: ToDosViewModel = viewModel(factory = AppViewModelProvider.Factory),
    imageRes: Int,
    color: Long,
    title: String,
    icon: Int,
    categoryId: Int,
    onNavBackClicked: () -> Unit,
){
    val todosUiState by viewModel.getCategoryItems(categoryId).collectAsState()
    val openDialog = remember { mutableStateOf(false) }
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy") // e.g., August 21, 2024
    val formattedDate = currentDate.format(formatter)
    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { openDialog.value = true },
                containerColor = if(imageRes != 0) FloatingActionButtonDefaults.containerColor
                                 else Color(color),
            ) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
            }
        }
    ) {
        PaddingValues(it)
        AddTodoDialog(
            openDialog = openDialog,
            color = if (imageRes != 0) null else color,
            onAddItemClicked = { todoName ->  
                viewModel.addTodo(
                    Item(
                        categoryId = categoryId,
                        name = todoName,
                        isCompleted = false,
                        isFavorite = false,
                        date = formattedDate
                    )
                )
            }
        )
        TodoBody(
            imageRes = imageRes,
            color = color,
            icon = icon,
            title = title,
            todosList = todosUiState.todosList,
            onNavBackClicked = onNavBackClicked
        )
    }
}

@Composable
fun PaddingValues(paddingValues: PaddingValues){}

@Composable
fun TodoBody(
    modifier: Modifier = Modifier,
    imageRes: Int,
    color: Long,
    icon: Int,
    title: String,
    todosList: List<Item>,
    onNavBackClicked: () -> Unit,
){
    val coroutineScope = rememberCoroutineScope()
    var itemsLoaded by remember {
        mutableStateOf(false)
    }
    // TODO: move launched effect to todo screen navigation
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            delay(1000L)
            itemsLoaded = true
        }
    }
    Log.d("todo recomposed","recomposition")
    Box {
        if(imageRes != 0){
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = modifier.fillMaxSize(),
                contentScale = ContentScale.FillHeight
            )
        }
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Spacer(modifier = modifier.height(30.dp))
            TodosTopAppBar(
                onNavBackClicked = onNavBackClicked,
                colorSelected = if(imageRes != 0) null else Color(color),
                icon = icon,
                imageRes = imageRes,
                title = title,
                onMoreClicked = {}
            )
            Spacer(modifier = modifier.height(10.dp))
            if (itemsLoaded){
                if (imageRes == 0 && todosList.isEmpty()){
                    Column(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(top = 80.dp,),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.icon_3d_6),
                            contentDescription = null
                        )
                        Spacer(modifier = modifier.height(50.dp))
                        Text(
                            text = "You haven't added any task to this list yet",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Black,
                            textAlign = TextAlign.Center,
                            color = Color(color),
                            modifier = modifier.padding(start = 60.dp, end = 60.dp)
                        )
                    }
                }
            }
            TodoItemList(todosList = todosList)
        }
    }
}

@Composable
fun TodoItemList(
    modifier: Modifier = Modifier,
    todosList: List<Item>
){
    LazyColumn(
        contentPadding = PaddingValues(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(todosList){
            TodoItem(item = it)
        }
    }
}
@Composable
fun TodoItem(
    modifier: Modifier = Modifier,
    item: Item,
){
    Card(
        onClick = { /*TODO*/ },
        modifier = modifier.fillMaxWidth(),
        shape = CutCornerShape(0)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = false,
                onClick = {  },
            )
            Text(
                text = item.name,
                maxLines = 1,
                fontWeight = FontWeight.Black,
            )
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(end = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = if(item.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = null
                    )
                }
            }
        }
    }
}
