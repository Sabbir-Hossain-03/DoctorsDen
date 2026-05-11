package com.example.daapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.daapp.model.DoctorModel
import com.example.daapp.repository.MainRepository

class MainViewmodel : ViewModel() {
    private val repository = MainRepository()

    fun loadDoctors(): LiveData<MutableList<DoctorModel>> {
        return repository.load()
    }
}