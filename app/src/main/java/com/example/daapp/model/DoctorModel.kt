package com.example.daapp.model

import android.os.Parcel
import android.os.Parcelable

data class DoctorModel(
    val Address: String = "",
    val Biography: String = "",
    val Id: Int = 0,
    val Name: String = "",
    val Picture: String = "",
    val Special: String = "",
    val Expriense: Int = 0,
    val Cost: String = "",
    val Date: String = "",
    val Time: String = "",
    val Location: String = "",
    val Mobile: String = "",
    val Patiens: String = "",
    val Rating: Double = 0.0,
    val Site: String = ""
) : Parcelable {

    companion object CREATOR : Parcelable.Creator<DoctorModel> {
        override fun createFromParcel(parcel: Parcel): DoctorModel {
            TODO("Not yet implemented")
        }

        override fun newArray(size: Int): Array<DoctorModel?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        TODO("Not yet implemented")
    }
}