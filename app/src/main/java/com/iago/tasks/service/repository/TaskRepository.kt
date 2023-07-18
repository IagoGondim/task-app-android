package com.iago.tasks.service.repository

import android.content.Context
import com.iago.tasks.R
import com.iago.tasks.service.listener.APIListener
import com.iago.tasks.service.model.TaskModel
import com.iago.tasks.service.repository.remote.RetrofitClient
import com.iago.tasks.service.repository.remote.TaskService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskRepository(context: Context) : BaseRepository(context) {
  private val remote = RetrofitClient.getService(TaskService::class.java)
  
  fun create(task: TaskModel, listener: APIListener<Boolean>) {
    val call = remote.create(task.priorityId, task.description, task.dueDate, task.complete)
    executeCall(call, listener)
  }
  
  fun list(listener: APIListener<List<TaskModel>>) {
    executeCall(remote.listNext(), listener)
  }
  
  fun listNext(listener: APIListener<List<TaskModel>>) {
    executeCall(remote.listNext(), listener)
  }
  
  fun listOverdue(listener: APIListener<List<TaskModel>>) {
    executeCall(remote.listNext(), listener)
  }
  
  fun delete(id: Int, listener: APIListener<Boolean>) {
    executeCall(remote.delete(id), listener)

  }
  
}