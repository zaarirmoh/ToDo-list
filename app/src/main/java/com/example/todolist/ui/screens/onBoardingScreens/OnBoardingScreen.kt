package com.example.todolist.ui.screens.onBoardingScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.R

@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    id: Int = R.drawable.onboarding1,
    title: String = "Manage your tasks",
    description: String = "You can easily manage all of your daily tasks in DoMe for free",
    onSkipClicked: () -> Unit = {},
    onNextClicked: () -> Unit = {}
){
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = modifier.height(100.dp))
        Image(
            painter = painterResource(id = id),
            contentDescription = null,
            modifier = modifier.height(300.dp)
        )
        Spacer(modifier = modifier.height(90.dp))
        OnBoardingIndicator(id = id)
        Spacer(modifier = modifier.height(50.dp))
        Text(
            text = title,
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            maxLines = 1
        )
        Spacer(modifier = modifier.height(30.dp))
        Text(
            text = description,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(start = 50.dp, end = 50.dp)
        )
        Column(
            modifier = modifier.fillMaxSize().padding(bottom = 20.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OnBoardingButtons(
                buttonText = if(id == R.drawable.onboarding3) "Get started" else "Next",
                onNextClicked = onNextClicked,
                onSkipClicked = onSkipClicked
            )
        }
    }
}
@Composable
fun OnBoardingIndicator(
    modifier: Modifier = Modifier,
    id: Int
){
    val onBoardingNumber = when(id){
        R.drawable.onboarding1 -> 0
        R.drawable.onboarding2 -> 1
        else -> 2
    }
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        HorizontalDivider(
            modifier = modifier.width(40.dp),
            thickness = 5.dp,
            color = if(onBoardingNumber == 0) MaterialTheme.colorScheme.primary else DividerDefaults.color
        )
        Spacer(modifier = modifier.width(5.dp))
        HorizontalDivider(
            modifier = modifier.width(40.dp),
            thickness = 5.dp,
            color = if(onBoardingNumber == 1) MaterialTheme.colorScheme.primary else DividerDefaults.color
        )
        Spacer(modifier = modifier.width(5.dp))
        HorizontalDivider(
            modifier = modifier.width(40.dp),
            thickness = 5.dp,
            color = if(onBoardingNumber == 2) MaterialTheme.colorScheme.primary else DividerDefaults.color
        )
    }
}
@Composable
fun OnBoardingButtons(
    modifier: Modifier = Modifier,
    buttonText: String,
    onSkipClicked: () -> Unit = {},
    onNextClicked: () -> Unit = {}
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        TextButton(onClick = onSkipClicked) {
            Text(
                text = "Skip",
                modifier = modifier.padding(10.dp)
            )
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = onNextClicked) {
                Text(
                    text = buttonText,
                    modifier = modifier.padding(10.dp)
                )
            }
        }
    }
}