package com.iago.tasks.service.repository

import android.content.Context
import com.google.gson.Gson
import com.iago.tasks.R
import com.iago.tasks.service.constants.TaskConstants
import com.iago.tasks.service.listener.APIListener
import com.iago.tasks.service.model.PriorityModel
import com.iago.tasks.service.repository.local.TaskDatabase
import com.iago.tasks.service.repository.remote.PriorityService
import com.iago.tasks.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PriorityRepository(val context: Context) {
  
  private val remote = RetrofitClient.getService(PriorityService::class.java)
  private val database = TaskDatabase.getDatabase(context).priorityDao()
  
  
  fun list(listener: APIListener<List<PriorityModel>>) {
    val call = remote.list()
    call.enqueue(object : Callback<List<PriorityModel>> {
      override fun onResponse(
        call: Call<List<PriorityModel>>,
        response: Response<List<PriorityModel>>
      ) {
        if (response.code() == TaskConstants.HTTP.SUCCESS) {
          response.body()?.let { listener.onSuccess(it) }
        } else {
          listener.onFailure(failResponse(response.errorBody()!!.string()))
        }
      }
      
      override fun onFailure(call: Call<List<PriorityModel>>, t: Throwable) {
        listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
      }
    })
  }
  
  fun save(list: List<PriorityModel>) {
    database.clear()
    database.save(list)
  }
  
  private fun failResponse(str: String): String {
    return Gson().fromJson(str, String::class.java)
  }
}