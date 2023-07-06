package com.iago.tasks.service.repository

import android.content.Context
import com.iago.tasks.R
import com.iago.tasks.service.listener.APIListener
import com.iago.tasks.service.model.PriorityModel
import com.iago.tasks.service.repository.local.TaskDatabase
import com.iago.tasks.service.repository.remote.PriorityService
import com.iago.tasks.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PriorityRepository(val context: Context) : BaseRepository() {
  
  private val remote = RetrofitClient.getService(PriorityService::class.java)
  private val database = TaskDatabase.getDatabase(context).priorityDao()
  
  fun list(listener: APIListener<List<PriorityModel>>) {
    val call = remote.list()
    call.enqueue(object : Callback<List<PriorityModel>> {
      override fun onResponse(
        call: Call<List<PriorityModel>>,
        response: Response<List<PriorityModel>>
      ) {
        handleResponse(response, listener)
      }
      
      override fun onFailure(call: Call<List<PriorityModel>>, t: Throwable) {
        listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
      }
    })
  }
  
  fun list(): List<PriorityModel> {
    return database.list()
  }
  
  fun save(list: List<PriorityModel>) {
    database.clear()
    database.save(list)
  }
}