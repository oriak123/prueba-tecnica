package com.example.trabajo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginActivity : AppCompatActivity() {

    private lateinit var txtUser: EditText
    private lateinit var txtPassword: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var auth:FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.primera_activity)

        txtUser=findViewById(R.id.txtUser)
        txtPassword=findViewById(R.id.txtPassword)
        progressBar=findViewById(R.id.progressBar)
        auth= FirebaseAuth.getInstance()
    }


    object ServiceBuilder {
        private val client = OkHttpClient.Builder().build()

        private val retrofit = Retrofit.Builder()
            .baseUrl("https://serveless.proximateapps-services.com.mx/proximatetools/dev/webadmin/testproximate/login")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        fun<T> buildService(service: Class<T>): T{
            return retrofit.create(service)
        }
    }

    fun sendPostRequest(view: View) {
        addDummyUser()
    }

    fun addDummyUser() {
        val user:String=txtUser.text.toString()
        val password:String=txtPassword.text.toString()

        val apiService = RestApiService()
        val userInfo = UserInfo(
            user = "1075258635",
            password = "testProximate"
        )

        apiService.addUser(userInfo) {
            if (it?.user != null) {
                // it = newly added user parsed as response
                // it?.id = newly added user ID
            } else {
                print("Error registering new user")
            }
        }
    }


    fun forgotPassword(view: View){
        startActivity(Intent(this,ForgotPassActivity::class.java))
    }
    fun register(view: View){

        startActivity(Intent(this,RegisterActivity::class.java))
    }


    fun login(view: View){
        loginUser()
    }
    private fun loginUser(){
        val user:String=txtUser.text.toString()
        val password:String=txtPassword.text.toString()

        if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password)){
            progressBar.visibility=View.VISIBLE

            auth.signInWithEmailAndPassword(user,password)
                .addOnCompleteListener(this){
                    task ->

                    if(task.isSuccessful){
                        action()
                    }else{
                        Toast.makeText(this,"Error en la autenticación", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
    private fun action (){
        startActivity(Intent(this,MainActivity::class.java))
    }
}