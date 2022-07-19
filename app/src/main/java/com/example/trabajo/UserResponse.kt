package com.example.trabajo

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class UserResponse(
    val codeStatus: String,
    @SerializedName("data")
    val _data: String?="",
    val message: String,
    val status: Boolean,
    val Convert: String,

    ){
    val dataUser: DataUser?
        get() = Gson().fromJson(_data, DataUser::class.java)?:   DataUser()
}