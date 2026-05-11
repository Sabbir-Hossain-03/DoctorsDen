package com.example.daapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.daapp.model.DoctorModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.concurrent.thread

class MainRepository {
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val auth = FirebaseAuth.getInstance()

    fun load(): LiveData<MutableList<DoctorModel>> {
        val listData = MutableLiveData<MutableList<DoctorModel>>()
        val ref = firebaseDatabase.getReference("Doctors")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                // Perform parsing in a background thread to keep UI smooth
                thread {
                    val list = mutableListOf<DoctorModel>()
                    for (snapshot in p0.children) {
                        val doctor = snapshot.getValue(DoctorModel::class.java)
                        doctor?.let { list.add(it) }
                    }
                    // Use postValue to update LiveData from a background thread
                    listData.postValue(list)
                }
            }

            override fun onCancelled(p0: DatabaseError) {}
        })
        return listData
    }

    fun makeAppointment(doctor: DoctorModel, onComplete: (Boolean) -> Unit) {
        val userId = auth.currentUser?.uid ?: return
        val appointmentRef = firebaseDatabase.getReference("Appointments").push()
        
        val appointmentData = mapOf(
            "userId" to userId,
            "doctorId" to doctor.Id,
            "doctorName" to doctor.Name,
            "date" to doctor.Date,
            "time" to doctor.Time,
            "timestamp" to System.currentTimeMillis()
        )

        appointmentRef.setValue(appointmentData).addOnCompleteListener { task ->
            onComplete(task.isSuccessful)
        }
    }
}