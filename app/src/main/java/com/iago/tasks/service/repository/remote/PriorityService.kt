package com.iago.tasks.service.repository.remote

import com.iago.tasks.service.model.PriorityModel
import retrofit2.http.GET
import retrofit2.Call


interface PriorityService {
  
  
  @GET("Priority")
  fun list(): Call<List<PriorityModel>>
}