package com.example.myapplication.wellness

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun wellnessTaskItem(
    modifier: Modifier = Modifier,
    checked: Boolean,
    task: WellnessTaskItemList,
    onCheckedChanged: (Boolean) -> Unit,
    onCloseClicked: (WellnessTaskItemList) -> Unit
) {

    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = task.taskTitle, style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )

        Checkbox(checked = checked, onCheckedChange = onCheckedChanged)
        IconButton(
            onClick = {
                      onCloseClicked(task)
            },
        ) {
            Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
        }
    }
}

/*@Composable
fun wellnessTaskItem(
    task: WellnessTaskItemList,
    onCloseClicked: (WellnessTaskItemList, Boolean) -> Unit,
    onCheckedChanged: (WellnessTaskItemList) -> Unit
) {

    wellnessTaskItem(
        modifier = Modifier,
        task = task,
        onCheckedChanged = onCheckedChanged,
        onCloseClicked = onCloseClicked
    )
}*/

@Composable
fun wellnessTaskList(
    modifier: Modifier = Modifier,
    list: MutableList<WellnessTaskItemList>,
    onCloseClicked: (WellnessTaskItemList) -> Unit,
    onCheckedChanged: (WellnessTaskItemList, Boolean) -> Unit
) {
        LazyColumn(){
            items(items = list,
                key = {task -> task.id}){task ->
                wellnessTaskItem(task = task, checked = task.checked, onCheckedChanged = {checked -> onCheckedChanged(task, checked)},  onCloseClicked = onCloseClicked)
            }
        }
}

class WellnessTaskItemList(
    val id: Int,
    val taskTitle: String,
    var initialCheckValue : Boolean = false
){
    var checked by mutableStateOf(initialCheckValue)
}
