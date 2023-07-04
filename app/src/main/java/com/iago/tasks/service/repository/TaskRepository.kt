package com.iago.tasks.service.repository

import com.iago.tasks.service.repository.remote.RetrofitClient
import com.iago.tasks.service.repository.remote.TaskService

class TaskRepository {
  private val remote = RetrofitClient.getService(TaskService::class.java)
}