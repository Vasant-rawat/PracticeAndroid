package com.example.weather.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.weather.R
import com.example.weather.model.Weather
import com.example.weather.model.WeatherItem
import com.example.weather.utils.fahrenheitToCelsius
import com.example.weather.utils.formatDate
import com.example.weather.utils.formatDateTime
import com.example.weather.utils.formatDecimals

@Composable
fun MainContent(data: Weather, paddingValue: PaddingValues) {
    val weatherItem = data.list[0]
    val imageUrl = "https://openweathermap.org/img/wn/${weatherItem.weather[0].icon}.png"
    Column(
        modifier = Modifier
            .padding(paddingValue)
            .padding(4.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formatDate(weatherItem.dt),
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(6.dp)
        )
        Surface(
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp),
            shape = CircleShape, color = Color(0xFFFFC400)
        ) {
            Column(
                modifier = Modifier.padding(1.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                WeatherStateImage(imageUrl = imageUrl)
                Text(
                    text = formatDecimals(fahrenheitToCelsius(weatherItem.temp.day)) + "°",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(text = weatherItem.weather[0].main, fontStyle = FontStyle.Italic)
            }
        }
        HumidityWindPressureRow(weather = weatherItem)
        HorizontalDivider()
        SunSetSunRiseRow(weather = weatherItem)
        Text("This Week", fontStyle = FontStyle.Normal, fontWeight = FontWeight.ExtraBold)
        WeatherDetailColumn(weather = data)
    }
}

@Composable
fun SunSetSunRiseRow(weather: WeatherItem) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row() {
            Icon(
                painter = painterResource(R.drawable.img_3), contentDescription = "sunrise",
                modifier = Modifier.size(20.dp)
            )
            Text("${formatDateTime(weather.sunrise)}")
        }
        Row() {
            Icon(
                painter = painterResource(R.drawable.img_4),
                contentDescription = "sunrise",
                modifier = Modifier.size(20.dp)
            )
            Text("${formatDateTime(weather.sunset)}")
        }
    }
}

@Composable
fun HumidityWindPressureRow(weather: WeatherItem) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.humidity),
                contentDescription = "humidity",
                modifier = Modifier.size(20.dp)
            )
            Text(text = "${weather.humidity}", modifier = Modifier.padding(horizontal = 4.dp))
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.img_2), contentDescription = "pressure",
                modifier = Modifier.size(20.dp)
            )
            Text(text = "${weather.pressure} psi")
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.img), contentDescription = "wind",
                modifier = Modifier.size(20.dp)
            )
            Text(text = "${weather.humidity} mph")
        }

    }
}

@Composable
fun WeatherStateImage(imageUrl: String) {
    Image(
        painter = rememberAsyncImagePainter(model = imageUrl),
        contentDescription = "Icon image",
        modifier = Modifier.size(80.dp)
    )
}


@Composable
fun WeatherDetailColumn(weather: Weather) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFEEF1EF),
        shape = RoundedCornerShape(size = 13.dp)
    ) {
        LazyColumn(modifier = Modifier.padding(2.dp), contentPadding = PaddingValues(1.dp)) {
            items(items = weather.list) { index ->
                WeatherDetailRow(weather = index)
            }
        }
    }
}

@Composable
fun WeatherDetailRow(weather: WeatherItem) {
    val imageUrl = "https://openweathermap.org/img/wn/${weather.weather[0].icon}.png"
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        shape = CircleShape.copy(topEnd = CornerSize(6.dp))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = formatDate(weather.dt).split(",")[0])
            WeatherStateImage(imageUrl)
            Surface(
                modifier = Modifier.padding(0.dp),
                shape = CircleShape, color = Color(0xFFFFC400)
            ) {
                Text(
                    weather.weather[0].description,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.titleSmall
                )

            }
            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Blue.copy(alpha = 0.7f),
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append(formatDecimals(fahrenheitToCelsius(weather.temp.max)) + "°")
                }
                withStyle(style = SpanStyle(color = Color.LightGray)) {
                    append(formatDecimals(fahrenheitToCelsius(weather.temp.min)))
                }
            })
        }
    }

}