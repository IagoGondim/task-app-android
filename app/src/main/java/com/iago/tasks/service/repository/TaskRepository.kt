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
}