package com.iago.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iago.tasks.service.listener.APIListener
import com.iago.tasks.service.model.PriorityModel
import com.iago.tasks.service.model.TaskModel
import com.iago.tasks.service.model.ValidationModel
import com.iago.tasks.service.repository.PriorityRepository
import com.iago.tasks.service.repository.TaskRepository

class TaskFormViewModel(application: Application) : AndroidViewModel(application) {
  
  private val priorityRepository = PriorityRepository(application.applicationContext)
  private val taskRepository = TaskRepository(application.applicationContext)
  
  
  private val _priorityList = MutableLiveData<List<PriorityModel>>()
  val priorityList: LiveData<List<PriorityModel>> = _priorityList
  
  private val _taskSave = MutableLiveData<ValidationModel>()
  val taskSave: LiveData<ValidationModel> = _taskSave
  
  private val _task = MutableLiveData<TaskModel>()
  val task: LiveData<TaskModel> = _task
  
  private val _taskLoad = MutableLiveData<ValidationModel>()
  val taskLoad: LiveData<ValidationModel> = _taskLoad
  
  fun save(task: TaskModel) {
    val listener = object : APIListener<Boolean> {
      override fun onSuccess(result: Boolean) {
        _taskSave.value = ValidationModel()
      }
      
      override fun onFailure(message: String) {
        _taskSave.value = ValidationModel(message)
      }
      
    }
    
    if (task.id == 0) {
      
      taskRepository.create(task, listener)
    } else {
      taskRepository.update(task, listener)
    }
  }
  
  fun load(id: Int) {
    taskRepository.load(id, object : APIListener<TaskModel> {
      override fun onSuccess(result: TaskModel) {
        _task.value = result
      }
      
      override fun onFailure(message: String) {
        _taskLoad.value = ValidationModel(message)
      }
      
    })
  }
  
  fun loadPriorities() {
    _priorityList.value = priorityRepository.list()
  }
  
}