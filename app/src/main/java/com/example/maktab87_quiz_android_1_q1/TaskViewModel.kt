package com.example.maktab87_quiz_android_1_q1

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

class TaskViewModel: ViewModel() {
    var tasksItems = MediatorLiveData<MutableList<TaskItem>?>()
    init {
        tasksItems.value = mutableListOf()
    }
    fun addTaskItem(newTask: TaskItem){
        val list = tasksItems.value
        list!!.add(newTask)
        tasksItems.postValue(list)
    }
    fun updateTaskItems(id:UUID,name:String,desc:String,time:LocalTime?){
        val list = tasksItems.value
        val task = list!!.find{it.id === id}!!
        task.name = name
        task.desc = desc
        task.dueTime = time
        tasksItems.postValue(list)
    }
    fun setCompleted(taskItems: TaskItem){
        val list = tasksItems.value
        val task = list!!.find { it.id == taskItems.id }!!
        if (task.completedDate == null)
            task.completedDate = LocalDate.now()
        tasksItems.postValue(list)
    }
}