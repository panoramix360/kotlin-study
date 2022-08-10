package com.kotlinstudy.shapedisplay

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinstudy.shapedisplay.model.Location
import com.kotlinstudy.shapedisplay.model.Shape
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.NonCancellable.start
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.random.Random

@HiltViewModel
class MainViewModel : ViewModel() {
    private val shapes = Channel<Shape>()
    private val locations = Channel<Location>()
    private var count = 0

    fun load() = viewModelScope.launch {
        with(ShapeCollector(4)) {
            start(locations, shapes)
            consumeShapes(shapes)
        }
    }

    fun sendLocations() = sendLocations(locations)

    private fun consumeShapes(
        shapesInput: ReceiveChannel<Shape>
    ) = viewModelScope.launch {
        for (shape in shapesInput) {
            count++
        }
    }

    private fun sendLocations(
        locationsOutput: SendChannel<Location>
    ) = viewModelScope.launch {
        withTimeoutOrNull(3000) {
            while(true) {
                // simulate fetching data
                val location = Location(Random.nextInt(), Random.nextInt())
                Log.d("MainViewModel", "Sending a new location")
                locationsOutput.send(location)
            }
        }
        Log.d("MainViewModel", "Received $count shapes")
    }
}