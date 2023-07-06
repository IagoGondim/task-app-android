package com.iago.tasks.service.repository

import android.content.Context
import com.iago.tasks.R
import com.iago.tasks.service.listener.APIListener
import com.iago.tasks.service.model.PersonModel
import com.iago.tasks.service.repository.remote.PersonService
import com.iago.tasks.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class PersonRepository(val context: Context) : BaseRepository() {
  
  private val remote = RetrofitClient.getService(PersonService::class.java)
  
  fun login(email: String, password: String, listener: APIListener<PersonModel>) {
    val call = remote.login(email, password)
    call.enqueue(object : retrofit2.Callback<PersonModel> {
      override fun onResponse(call: Call<PersonModel>, response: Response<PersonModel>) {
        handleResponse(response, listener)
      }
      
      override fun onFailure(call: Call<PersonModel>, t: Throwable) {
        listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
      }
    })
  }
}
