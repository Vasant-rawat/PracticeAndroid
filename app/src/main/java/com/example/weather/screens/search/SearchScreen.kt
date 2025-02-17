package com.example.weather.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weather.navigation.WeatherScreens
import com.example.weather.widgets.WeatherAppBar

@Composable
fun SearchScreen(navController: NavController) {
    Scaffold(topBar = {
        WeatherAppBar(
            navcontroller = navController, title = "Search", icon = Icons.Default.ArrowBackIosNew,
            isMainScreen = false
        ) {
            navController.popBackStack()
        }
    }) { contentPadding ->
        Surface(modifier = Modifier.padding(contentPadding)) { }
        Column(
            modifier = Modifier.padding(contentPadding), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)){mcity->
                navController.navigate(WeatherScreens.MainScreen.name+"/${mcity}")
            }
        }
    }
}


@Composable
fun SearchBar(modifier: Modifier = Modifier, onSearch: (String) -> Unit = {}) {
    val searchQueryState = rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val validate = remember(searchQueryState.value) {
        searchQueryState.value.trim().isNotEmpty()

    }
    Column {
        CommonTextField(valueState = searchQueryState,
            placeHolder = "Jalgaon", onaction = KeyboardActions {
                if (!validate) return@KeyboardActions
                onSearch(searchQueryState.value.trim())
                searchQueryState.value = ""
                keyboardController?.hide()
            })

    }
}

@Composable
fun CommonTextField(
    valueState: MutableState<String>,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    placeHolder: String,
    onaction: KeyboardActions
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { value ->
            valueState.value = value
        },
        label = { Text(text = placeHolder) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onaction,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            cursorColor = Color.Black
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .fillMaxWidth()


    )
}

