package com.iago.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iago.tasks.service.listener.APIListener
import com.iago.tasks.service.model.TaskModel
import com.iago.tasks.service.model.ValidationModel
import com.iago.tasks.service.repository.TaskRepository

class TaskListViewModel(application: Application) : AndroidViewModel(application) {
  
  private val taskRepository = TaskRepository(application.applicationContext)
  
  private val _tasks = MutableLiveData<List<TaskModel>>()
  val tasks: MutableLiveData<List<TaskModel>> = _tasks
  
  fun list() {
    taskRepository.list(object : APIListener<List<TaskModel>> {
      override fun onSuccess(result: List<TaskModel>) {
        _tasks.value = result
      }
      
      override fun onFailure(message: String) {
      
      }
      
    })
  }
  
}