package com.iago.tasks.service.listener

interface APIListener<T> {
  
  fun onSuccess(result: T)
  fun onFailure(message: String)
  
  
}