package com.iago.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.iago.tasks.service.listener.APIListener
import com.iago.tasks.service.model.TaskModel
import com.iago.tasks.service.model.ValidationModel
import com.iago.tasks.service.repository.PriorityRepository
import com.iago.tasks.service.repository.TaskRepository

class TaskListViewModel(application: Application) : AndroidViewModel(application) {
  
  private val taskRepository = TaskRepository(application.applicationContext)
  private val priorityRepository = PriorityRepository(application.applicationContext)
  
  
  private val _tasks = MutableLiveData<List<TaskModel>>()
  val tasks: MutableLiveData<List<TaskModel>> = _tasks
  
  private val _delete = MutableLiveData<ValidationModel>()
  val delete: MutableLiveData<ValidationModel> = _delete
  
  private val _status = MutableLiveData<ValidationModel>()
  val status: MutableLiveData<ValidationModel> = _status
  
  
  fun list() {
    taskRepository.list(object : APIListener<List<TaskModel>> {
      override fun onSuccess(result: List<TaskModel>) {
        result.forEach {
          it.priorityDescription = priorityRepository.getDescription(it.priorityId)
        }
        
        _tasks.value = result
      }
      
      override fun onFailure(message: String) {
      }
    })
  }
  
  fun delete(id: Int) {
    taskRepository.delete(id, object : APIListener<Boolean> {
      override fun onSuccess(result: Boolean) {
        list()
      }
      
      override fun onFailure(message: String) {
        _delete.value = ValidationModel(message)
      }
      
    })
  }
  
  fun status(id: Int, complete: Boolean) {
    val listener = object : APIListener<Boolean> {
      override fun onSuccess(result: Boolean) {
        list()
      }
      
      override fun onFailure(message: String) {
        _status.value = ValidationModel(message)
      }
      
    }
    
    if (complete) {
      taskRepository.complete(id, listener)
    } else {
      taskRepository.undo(id, listener)
    }
    
  }
  
}