package com.example.trabajo

import com.google.gson.annotations.SerializedName

data class UserInfo (
    // indicarme como se llama realmente en la base de datos

    @SerializedName("user") val user: String?,//// El seralizedName es para poder modificar el nombre de la VARIABLE como yo quiera
    @SerializedName("password") val password: String?,

)