package com.example.myapplication.wellness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.sideEffectsDemo.sideEffectDemo
import com.example.myapplication.wellness.ui.theme.MyApplicationTheme

class MyWellnessApp : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   sideEffectDemo()
                }
            }
        }
    }
}



@Composable
fun wellnessScreen(
    modifier : Modifier = Modifier,
    viewModel: WellnessViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
){
    Column {
        statefulWaterCounter()

        wellnessTaskList(list = viewModel.tasks, onCheckedChanged  = {task, checked -> viewModel.changeTaskChecked(task, checked)}, onCloseClicked = {task: WellnessTaskItemList -> viewModel.remove(task)} )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
       sideEffectDemo()
    }
}