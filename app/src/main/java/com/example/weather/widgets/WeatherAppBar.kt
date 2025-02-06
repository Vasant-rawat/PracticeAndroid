package com.example.weather.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon: Icons? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navcontroller: NavController,
    onAddActionClicked: () -> Unit = {},
    onButtonClick: () -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(
                text = title, color = MaterialTheme.colorScheme.secondary,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp)
            )
        },
        navigationIcon = {},
        actions = {
            if (isMainScreen) {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "search Icons ")
                }
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "search Icons ")

                }
            }
                else Box{}

        },
    )
}

