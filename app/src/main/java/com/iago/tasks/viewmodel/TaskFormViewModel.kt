package com.iago.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iago.tasks.service.model.PriorityModel
import com.iago.tasks.service.repository.PriorityRepository

class TaskFormViewModel(application: Application) : AndroidViewModel(application) {
  
  private val priorityRepository = PriorityRepository(application.applicationContext)
  
  
  private val _priorityList = MutableLiveData<List<PriorityModel>>()
  val priorityList: LiveData<List<PriorityModel>> = _priorityList
  
  fun loadPriorities() {
    _priorityList.value = priorityRepository.list()
  }
  
}