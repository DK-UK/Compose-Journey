package com.example.myapplication.wellness

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class WellnessViewModel : ViewModel() {

    private val _task : MutableList<WellnessTaskItemList> = wellnessTasks().toMutableStateList()
    val tasks : MutableList<WellnessTaskItemList>
        get() = _task

    fun remove(item : WellnessTaskItemList){
        _task.remove(item)
    }

    fun changeTaskChecked(item: WellnessTaskItemList, checked : Boolean){
        _task.find {
            it.id == item.id
        }?.let {
            it.checked = checked
        }
    }

    fun wellnessTasks() = List(30) { it -> WellnessTaskItemList(it, "task #$it") }
}