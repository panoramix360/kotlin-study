package com.kotlinstudy.shapedisplay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.kotlinstudy.shapedisplay.ui.theme.ShapeDisplayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShapeDisplayTheme {
                MainContent()
            }
        }
    }
}

@Composable
fun MainContent() {
    val mainViewModel = hiltViewModel<MainViewModel>()
    mainViewModel.load()

    val list = mutableListOf<Shape>()

    Column(
        modifier = Modifier
            .background(Color.Red)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            mainViewModel.sendLocations()
        }) {
            Text(text = stringResource(R.string.test_button))
        }

        LazyColumn {
            items(list) {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ShapeDisplayTheme {
        MainContent()
    }
}