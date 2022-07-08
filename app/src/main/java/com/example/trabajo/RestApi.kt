package com.example.trabajo
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface  RestApi {

    @Headers("Content-Type: application/json")
    @POST("/proximatetools/dev/webadmin/testproximate/login")
    fun addUser(@Body userData: UserInfo): Call<UserResponse>

    @Headers("Content-Type: application/json")
    @POST("proximatetools/dev/webadmin/testproximate/getproducts")
    fun getProducts (@Body userData: tokenGener): Call<valor>
}