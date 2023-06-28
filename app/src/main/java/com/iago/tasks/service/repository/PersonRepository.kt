package com.iago.tasks.service.repository

import android.content.Context
import com.google.gson.Gson
import com.iago.tasks.R
import com.iago.tasks.service.constants.TaskConstants
import com.iago.tasks.service.listener.APIListener
import com.iago.tasks.service.model.PersonModel
import com.iago.tasks.service.repository.remote.PersonService
import com.iago.tasks.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class PersonRepository(val context: Context) {
  
  private val remote = RetrofitClient.getService(PersonService::class.java)
  
  fun login(email: String, password: String, listener: APIListener<PersonModel>) {
    val call = remote.login(email, password)
    call.enqueue(object : retrofit2.Callback<PersonModel> {
      override fun onResponse(call: Call<PersonModel>, response: Response<PersonModel>) {
        if (response.code() == TaskConstants.HTTP.SUCCESS) {
          response.body()?.let { listener.onSuccess(it) }
        } else {
          
          listener.onFailure(failResponse(response.errorBody()!!.string()))
        }
      }
      
      override fun onFailure(call: Call<PersonModel>, t: Throwable) {
        listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
      }
      
    })
  }
  
  private fun failResponse(str: String): String {
    return Gson().fromJson(str, String::class.java)
  }
}
