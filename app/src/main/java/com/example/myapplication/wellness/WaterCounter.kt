package com.example.myapplication.wellness

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun statelessWaterCounter(modifier: Modifier = Modifier,
                          count : Int,
                          onClicked : () -> Unit) {

    Column(modifier = Modifier.padding(10.dp)) {

        if (count > 0) {
            Text(text = "you've had $count glasses.", style = MaterialTheme.typography.bodyLarge)
        }

        Button(onClick = onClicked, enabled = count < 10) {
            Text(text = "Add One")
        }

    }
}

@Composable
fun statefulWaterCounter(){
    var count by remember { mutableStateOf(0) }

    statelessWaterCounter(count = count, onClicked = {count++})
}