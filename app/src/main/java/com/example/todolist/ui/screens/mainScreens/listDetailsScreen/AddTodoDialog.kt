package com.example.todolist.ui.screens.mainScreens.listDetailsScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import com.example.todolist.R
import com.example.todolist.data.Item

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodoDialog(
    modifier: Modifier = Modifier,
    openDialog: MutableState<Boolean>,
    color: Long?,
    onAddItemClicked: (todoName: String) -> Unit
){
    val todoName = remember { mutableStateOf("") }
    if (openDialog.value){
        BasicAlertDialog(
            onDismissRequest = { openDialog.value = false }
        ) {
            val focusRequester = remember { FocusRequester() }
            val focusManager = LocalFocusManager.current
            val keyboardController = LocalSoftwareKeyboardController.current
            DisposableEffect(Unit) {
                focusRequester.requestFocus()
                keyboardController?.show()
                onDispose {}
            }
            Card(
                shape = RoundedCornerShape(3)
            ) {
                TextField(
                    value = todoName.value,
                    onValueChange = { todoName.value = it },
                    placeholder = { Text(text = "add a task") },
                    leadingIcon = {
                        RadioButton(
                            selected = false,
                            onClick = {  },
                            enabled = false
                        )
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                onAddItemClicked(todoName.value)
                                todoName.value = "" },
                            enabled = todoName.value != "",
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.icon_add_todo),
                                contentDescription = null,
                            )
                        }
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {
                        if (todoName.value != "") {
                            onAddItemClicked(todoName.value)
                            todoName.value = ""
                        }
                        else openDialog.value = false
                    }),
                    colors = TextFieldDefaults.colors(
                        cursorColor = if (color == null) Color.Unspecified else Color(color),
                        focusedIndicatorColor = if (color == null) Color.Unspecified else Color(color),
                        unfocusedIndicatorColor = if (color == null) Color.Unspecified else Color(color),
                    ),
                    modifier = modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester)
                )
            }
        }
    }
}