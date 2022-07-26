package com.example.trabajo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface  RestApi {

    @Headers("Content-Type: application/json") // Para el cliente
    @POST("/proximatetools/dev/webadmin/testproximate/login")
    fun addUser(@Body userData: UserInfo): Call<UserResponse>// la fun deberia llamarse getLogin
    // Body es porque asi lo requier el endpoint, nombre "userData" como nombre de la clase "UserInfo" en esa clase iria lo que requiere el endpoint que es el user y la clave
    // devuelve una llamada "call" de tipo UserResponse , eso se creo anteriormente donde recibimos los end point


    @Headers("Content-Type: application/json") // para el cliente
    @POST("proximatetools/dev/webadmin/testproximate/getproducts")
    fun getProducts (@Body userData: tokenGener): Call<valor> // Lo mismo que arriba
}