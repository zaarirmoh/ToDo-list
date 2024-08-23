package com.example.todolist.ui.screens.mainScreens.homeScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.R
import com.example.todolist.data.Category

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCategoryDialog(
    modifier: Modifier = Modifier,
    openDialog: MutableState<Boolean>,
    viewModel: HomeViewModel,
    onCancelClicked: () -> Unit,
    onCreateListClicked: () -> Unit,
){
    var colorSelected by remember { mutableStateOf(Color.Transparent) }
    var imageIdSelected by remember { mutableStateOf(0) }
    val categoryTitle = remember { mutableStateOf("") }
    val categoryIcon = remember { mutableStateOf(R.drawable.icon_3d_add) }
    val isColorSelected = remember { mutableStateOf(true) }
    val isIconSelected = remember { mutableStateOf(false) }
    val primaryColor = MaterialTheme.colorScheme.primary
    val tertiaryContainerColor = MaterialTheme.colorScheme.error
    colorSelected = primaryColor
    if (openDialog.value){
        BasicAlertDialog(
            onDismissRequest = { openDialog.value = false }
        ) {
            Card(
                shape = RoundedCornerShape(7)
            ) {
                Text(
                    text = "New list",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.padding(20.dp)
                )
                CategoryTitleRow(
                    categoryTitle = categoryTitle,
                    colorSelected = colorSelected,
                    iconSelected = categoryIcon.value,
                    onIconClicked = { isIconSelected.value = !isIconSelected.value }
                )
                if(!isIconSelected.value){
                    ColorOrImageRow(
                        isColorSelected = isColorSelected,
                        colorSelected = colorSelected
                    )
                    ColorOrImageDetailsRow(
                        isColorSelected = isColorSelected,
                        onColorClicked = { colorSelected = it },
                        onImageClicked = {
                            colorSelected = tertiaryContainerColor
                            imageIdSelected = it
                        }
                    )
                    DialogButtons(
                        inputValidated = if(categoryTitle.value.isBlank()) false else true,
                        colorSelected = colorSelected,
                        onCancelClicked = {
                            openDialog.value = false
                            onCancelClicked() },
                        onCreateListClicked = {
                            viewModel.addCategory(
                                Category(
                                    title = categoryTitle.value,
                                    color = colorSelected.toArgb().toLong(),
                                    photo = imageIdSelected,
                                    icon = categoryIcon.value,
                                    todos = 0
                                )
                            )
                            openDialog.value = false
                            onCreateListClicked()
                        }
                    )
                }else{
                    ChooseIconDetails(
                        colorSelected = colorSelected,
                        onIconClicked = {
                            categoryIcon.value = it
                            isIconSelected.value = false
                        },
                        onRemoveIconClicked = {
                            categoryIcon.value = R.drawable.icon_3d_add
                            isIconSelected.value = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryTitleRow(
    modifier: Modifier = Modifier,
    categoryTitle: MutableState<String>,
    colorSelected: Color,
    iconSelected: Int,
    onIconClicked: () -> Unit = {},
){
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    DisposableEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
        onDispose {}
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = onIconClicked,
            modifier = modifier
                .padding(start = 5.dp, end = 10.dp)
                .size(65.dp)
        ) {
            Image(
                painter = painterResource(id = iconSelected),
                contentDescription = null,
                modifier = modifier.size(35.dp)
            )
        }
        OutlinedTextField(
            value = categoryTitle.value,
            onValueChange = {categoryTitle.value = it},
            placeholder = { Text(text = "Enter list title")},
            singleLine = true,
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 10.dp,
                bottomStart = 10.dp,
                bottomEnd = 0.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorSelected,
                unfocusedBorderColor = colorSelected,
                cursorColor = colorSelected
            ),
            modifier = modifier
                .padding(end = 15.dp)
                .focusRequester(focusRequester)
        )
    }
}

@Composable
fun ColorOrImageRow(
    modifier: Modifier = Modifier,
    isColorSelected: MutableState<Boolean>,
    colorSelected: Color
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        OutlinedButton(
            onClick = {
                if(!isColorSelected.value)
                    isColorSelected.value = true },
            colors =
            if(!isColorSelected.value) ButtonDefaults.outlinedButtonColors(
                contentColor = colorSelected
            )
            else ButtonDefaults.buttonColors(
                containerColor = colorSelected,
                contentColor = MaterialTheme.colorScheme.onBackground
            ),
            border =
            if(!isColorSelected.value) BorderStroke(width = 3.dp, color = colorSelected)
            else null
        ) {
            Text(
                text = "Color",
                fontWeight = FontWeight.ExtraBold
            )
        }
        OutlinedButton(
            onClick = {
                if(isColorSelected.value)
                    isColorSelected.value = false },
            colors =
            if(isColorSelected.value) ButtonDefaults.outlinedButtonColors(
                contentColor = colorSelected
            )
            else ButtonDefaults.buttonColors(
                containerColor = colorSelected,
                contentColor = MaterialTheme.colorScheme.onBackground
            ),
            border =
            if(isColorSelected.value) BorderStroke(width = 3.dp, color = colorSelected)
            else null
        ) {
            Text(
                text = "Image",
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Composable
fun ColorOrImageDetailsRow(
    modifier: Modifier = Modifier,
    isColorSelected: MutableState<Boolean>,
    onColorClicked: (color: Color) -> Unit,
    onImageClicked: (imageId: Int) -> Unit,
){
    val colorList: List<Long> = listOf(
        0xFF95D5A7,0xFF3795BD,0xFF93000A,0xFFB99470,0xFFFF9100,0xFFC8A1E0,0xFFDFD3C3,
        0xFF387F39,0xFF4158A6,0xFFFF4E88
    )
    val imageList: List<Int> = listOf(
        R.drawable.todoimage1,R.drawable.todoimage2,R.drawable.todoimage3,
        R.drawable.todoimage4,R.drawable.todoimage5,R.drawable.todoimage6,
        R.drawable.todoimage7,R.drawable.todoimage8,R.drawable.todoimage9,
        R.drawable.todoimage10
    )
    LazyRow(
        contentPadding = PaddingValues(15.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        if(isColorSelected.value){
            items(colorList){
                Box(modifier = modifier
                    .size(30.dp)
                    .background(Color(it), RoundedCornerShape(100))
                    .clickable { onColorClicked(Color(it)) })
            }
        }else{
            items(imageList){
                Image(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .size(30.dp)
                        .clip(RoundedCornerShape(100))
                        .clickable { onImageClicked(it) }
                )
            }
        }
    }
}

@Composable
fun DialogButtons(
    modifier: Modifier = Modifier,
    inputValidated: Boolean,
    colorSelected: Color,
    onCancelClicked: () -> Unit,
    onCreateListClicked: () -> Unit,
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.End
    ) {
        TextButton(
            onClick = onCancelClicked,
        ) {
            Text(
                text = "Cancel",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = colorSelected
            )
        }
        TextButton(
            onClick = onCreateListClicked,
            enabled = inputValidated,
            colors = ButtonDefaults.textButtonColors(
                contentColor = colorSelected
            )
        ) {
            Text(
                text = "Create list",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun ChooseIconDetails(
    modifier: Modifier = Modifier,
    colorSelected: Color,
    onIconClicked: (categoryIconId: Int) -> Unit,
    onRemoveIconClicked: () -> Unit,
){
    val iconsList = listOf(
        R.drawable.icon_3d_1,R.drawable.icon_3d_2,R.drawable.icon_3d_3,
        R.drawable.icon_3d_4,R.drawable.icon_3d_5,R.drawable.icon_3d_6,
        R.drawable.icon_3d_7,R.drawable.icon_3d_8,R.drawable.icon_3d_9,
        R.drawable.icon_3d_10,R.drawable.icon_3d_11,R.drawable.icon_3d_12,
        R.drawable.icons8_batman_48,R.drawable.icons8_finn_48,R.drawable.icons8_idea_48,
        R.drawable.icons8_jake_48,R.drawable.icons8_key_48,R.drawable.icons8_tom_48,
        R.drawable.icons8_binoculars_48,R.drawable.icons8_delete_48,R.drawable.icons8_support_48,
        R.drawable.icons8_son_goku_48,R.drawable.icons8_document_48
    )
    Column {
        Spacer(modifier = modifier.height(10.dp))
        LazyVerticalGrid(
            columns = GridCells.Adaptive(50.dp),
            modifier = modifier.height(200.dp)
        ) {
            items(iconsList){
                IconButton(
                    onClick = { onIconClicked(it) },
                    modifier = modifier
                        .size(50.dp),
                ) {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = null,
                        modifier = modifier.size(30.dp)
                    )
                }
            }
        }
        Spacer(modifier = modifier.height(10.dp))
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(
                onClick = onRemoveIconClicked,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = colorSelected
                )
            ) {
                Text(
                    text = "Remove Emoji",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}