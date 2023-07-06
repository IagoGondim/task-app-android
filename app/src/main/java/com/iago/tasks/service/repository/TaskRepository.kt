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

class TaskRepository(val context: Context) : BaseRepository() {
  private val remote = RetrofitClient.getService(TaskService::class.java)
  
  fun create(task: TaskModel, listener: APIListener<Boolean>) {
    val call = remote.create(task.priorityId, task.description, task.dueDate, task.complete)
    call.enqueue(object : Callback<Boolean> {
      override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
        handleResponse(response, listener)
      }
      
      override fun onFailure(call: Call<Boolean>, t: Throwable) {
        listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
      }
    })
  }
  
  fun list(listener: APIListener<List<TaskModel>>) {
    val call = remote.list()
    list(call, listener)
  }
  
  fun listNext(listener: APIListener<List<TaskModel>>) {
    val call = remote.listNext()
    list(call, listener)
  }
  
  fun listOverdue(listener: APIListener<List<TaskModel>>) {
    val call = remote.listOverdue()
    list(call, listener)
  }
  
  private fun list(call: Call<List<TaskModel>>, listener: APIListener<List<TaskModel>>) {
    call.enqueue(object : Callback<List<TaskModel>> {
      override fun onResponse(call: Call<List<TaskModel>>, response: Response<List<TaskModel>>) {
        handleResponse(response, listener)
        
      }
      
      override fun onFailure(call: Call<List<TaskModel>>, t: Throwable) {
        listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
        
      }
      
    })
  }
  
}