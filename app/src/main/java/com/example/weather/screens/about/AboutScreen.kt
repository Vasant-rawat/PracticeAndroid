package com.example.weather.screens.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weather.R
import com.example.weather.navigation.WeatherScreens
import com.example.weather.widgets.WeatherAppBar


@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(topBar = {
        WeatherAppBar(
            onAddActionClicked = {
                navController.navigate(WeatherScreens.SearchScreen.name)
            },
            title = "About Screen",
            icon = Icons.Default.ArrowBackIosNew,
            isMainScreen = false,
            navcontroller = navController, elevation = 5.dp
        )
    }) { padding ->
        Surface(modifier = Modifier
            .padding(padding)
            .fillMaxWidth()
            .fillMaxHeight()) {
            Column (horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center){
                Text(text = stringResource( R.string.about_app), style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold)
                Text(text = stringResource( R.string.app_used), style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold)


            }
        }
    }
}