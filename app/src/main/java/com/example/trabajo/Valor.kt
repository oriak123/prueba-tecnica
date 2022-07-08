package com.example.trabajo

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class valor(
    val codeStatus: String,
    @SerializedName("data")
    val _data: String,
    val message: String,
    val status: Boolean
){
    val data: Data
        get() = Gson().fromJson(_data, Data::class.java)
}