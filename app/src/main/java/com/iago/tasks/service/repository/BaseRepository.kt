package com.iago.tasks.service.repository

import com.google.gson.Gson
import com.iago.tasks.service.constants.TaskConstants
import com.iago.tasks.service.listener.APIListener
import retrofit2.Response

open class BaseRepository {
  private fun failResponse(str: String): String {
    return Gson().fromJson(str, String::class.java)
  }
  
  fun <T> handleResponse(response: Response<T>, listener: APIListener<T>) {
    if (response.code() == TaskConstants.HTTP.SUCCESS) {
      response.body()?.let { listener.onSuccess(it) }
    } else {
      listener.onFailure(failResponse(response.errorBody()!!.string()))
    }
  }
}