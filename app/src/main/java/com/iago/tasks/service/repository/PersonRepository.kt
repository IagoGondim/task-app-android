package com.iago.tasks.service.repository

import android.content.Context
import com.iago.tasks.service.listener.APIListener
import com.iago.tasks.service.model.PersonModel
import com.iago.tasks.service.repository.remote.PersonService
import com.iago.tasks.service.repository.remote.RetrofitClient

class PersonRepository(context: Context) : BaseRepository(context) {
  
  private val remote = RetrofitClient.getService(PersonService::class.java)
  
  fun login(email: String, password: String, listener: APIListener<PersonModel>) {
    executeCall(remote.login(email, password), listener)
    
  }
}
