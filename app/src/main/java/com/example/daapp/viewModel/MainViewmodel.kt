package com.example.daapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.daapp.model.DoctorModel
import com.example.daapp.repository.MainRepository

class MainViewmodel : ViewModel() {
    private val repository = MainRepository()

    // Store the LiveData property so it's created only once
    val doctors: LiveData<MutableList<DoctorModel>> = repository.load()

    fun loadDoctors(): LiveData<MutableList<DoctorModel>> {
        return doctors
    }

    fun makeAppointment(doctor: DoctorModel, onComplete: (Boolean) -> Unit) {
        repository.makeAppointment(doctor, onComplete)
    }
}