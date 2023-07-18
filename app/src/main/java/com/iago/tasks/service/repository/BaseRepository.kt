package com.iago.tasks.service.repository

import android.content.Context
import com.google.gson.Gson
import com.iago.tasks.R
import com.iago.tasks.service.constants.TaskConstants
import com.iago.tasks.service.listener.APIListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseRepository(val context: Context) {
  private fun failResponse(str: String): String {
    return Gson().fromJson(str, String::class.java)
  }
  
  fun <T> executeCall(call: Call<T>, listener: APIListener<T>) {
    call.enqueue(object : Callback<T> {
      override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.code() == TaskConstants.HTTP.SUCCESS) {
          response.body()?.let { listener.onSuccess(it) }
        } else {
          listener.onFailure(failResponse(response.errorBody()!!.string()))
        }
      }
      
      override fun onFailure(call: Call<T>, t: Throwable) {
        listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
      }
    })
  }
}