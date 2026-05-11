package com.example.daapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.daapp.model.DoctorModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainRepository {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun load(): LiveData<MutableList<DoctorModel>> {
        val listData = MutableLiveData<MutableList<DoctorModel>>()
        val ref = firebaseDatabase.getReference("Doctors")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val list = mutableListOf<DoctorModel>()
                for (snapshot in p0.children) {
                    val doctor = snapshot.getValue(DoctorModel::class.java)
                    doctor?.let { list.add(it) }
                }
                listData.value = list
            }

            override fun onCancelled(p0: DatabaseError) {
                // You can add logging here if needed
            }
        })
        return listData
    }
}