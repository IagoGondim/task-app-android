package com.iago.tasks.service.repository.remote

import com.iago.tasks.service.model.TaskModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TaskService {
  
  @GET("Task")
  fun list(): Call<List<TaskModel>>
  
  @GET("Task/Next7Days")
  fun listNext(): Call<List<TaskModel>>
  
  @GET("Task/Overdue")
  fun listOverdue(): Call<List<TaskModel>>
  
  @GET("Task/{id}")
  fun load(@Path(value = "id", encoded = true) id: Int): Call<TaskModel>
  
  @POST("Task")
  @FormUrlEncoded
  fun create(
    @Field("PriorityId") priorityId: Int,
    @Field("Description") description: String,
    @Field("DueDate") dueDate: String,
    @Field("Complete") complete: Boolean,
  ): Call<Boolean>
  
  @PUT("Task")
  @FormUrlEncoded
  fun update(
    @Field("Id") Id: Int,
    @Field("PriorityId") priorityId: Int,
    @Field("Description") description: String,
    @Field("DueDate") dueDate: String,
    @Field("Complete") complete: Boolean,
  ): Call<Boolean>
  
  @PUT("Task/Complete")
  @FormUrlEncoded
  fun complete(
    @Field("Id") Id: Int,
  ): Call<Boolean>
  
  @PUT("Task/Complete")
  @FormUrlEncoded
  fun undo(
    @Field("Id") Id: Int,
  ): Call<Boolean>
  
  @HTTP(method = "DELETE", path = "Task", hasBody = true)
  @FormUrlEncoded
  fun delete(
    @Field("Id") Id: Int,
  ): Call<Boolean>
  
}