package com.iago.tasks.service.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.iago.tasks.service.model.PriorityModel


@Dao
interface PriorityDAO {
  
  @Insert
  fun save(list: List<PriorityModel>)
  
  @Query("SELECT * FROM Priority")
  fun list(): List<PriorityModel>
  
  @Query("DELETE FROM Priority")
  fun clear()
}