package com.iago.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iago.tasks.service.listener.APIListener
import com.iago.tasks.service.model.PersonModel
import com.iago.tasks.service.model.ValidationModel
import com.iago.tasks.service.repository.PersonRepository

class LoginViewModel(application: Application) : AndroidViewModel(application) {
  
  private val personRepository = PersonRepository(application.applicationContext)
  
  private val _login = MutableLiveData<ValidationModel>()
  val login: LiveData<ValidationModel> = _login
  
  
  /**
   * Faz login usando API
   */
  fun doLogin(email: String, password: String) {
    personRepository.login(email, password, object : APIListener<PersonModel> {
      override fun onSuccess(result: PersonModel) {
        _login.value = ValidationModel()
      }
      
      override fun onFailure(message: String) {
        _login.value = ValidationModel(message)
      }
      
    })
    
  }
  
  /**
   * Verifica se usuário está logado
   */
  fun verifyLoggedUser() {
  }
  
}