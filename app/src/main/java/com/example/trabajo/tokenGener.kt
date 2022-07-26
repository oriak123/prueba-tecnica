package com.example.trabajo

import com.google.gson.annotations.SerializedName

data class tokenGener(
    @SerializedName("userToken") val userToken: String // El seralizedName es para poder modificar el nombre de la VARIABLE como yo quiera
)
