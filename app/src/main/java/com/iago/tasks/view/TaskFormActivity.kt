package com.iago.tasks.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.iago.tasks.R
import com.iago.tasks.databinding.ActivityTaskFormBinding
import com.iago.tasks.viewmodel.TaskFormViewModel
import java.text.SimpleDateFormat
import java.util.Calendar

class TaskFormActivity : AppCompatActivity(), View.OnClickListener,
  DatePickerDialog.OnDateSetListener {
  
  private lateinit var viewModel: TaskFormViewModel
  private lateinit var binding: ActivityTaskFormBinding
  private val dateFormat = SimpleDateFormat("dd/MM/yyyy")
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    // Variáveis da classe
    viewModel = ViewModelProvider(this).get(TaskFormViewModel::class.java)
    binding = ActivityTaskFormBinding.inflate(layoutInflater)
    
    // Eventos
    binding.buttonSave.setOnClickListener(this)
    binding.buttonDate.setOnClickListener(this)
    
    // Layout
    setContentView(binding.root)
  }
  
  override fun onClick(v: View) {
    if (v.id == R.id.button_date)
      handleDate()
  }
  
  override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, dayOfMonth)
    
    val dueDate = dateFormat.format(calendar.time)
    binding.buttonDate.text = dueDate
    
    
  }
  
  private fun handleDate() {
    val calender = Calendar.getInstance()
    val year = calender.get(Calendar.YEAR)
    val month = calender.get(Calendar.MONTH)
    val day = calender.get(Calendar.DAY_OF_MONTH)
    DatePickerDialog(this, this, year, month, day).show()
  }
  
  
}