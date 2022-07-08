package com.example.trabajo

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import okhttp3.OkHttpClient
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginActivity : AppCompatActivity() {

    private lateinit var txtUser: EditText
    private lateinit var txtPassword: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.primera_activity)

        txtUser = findViewById(R.id.txtUser)
        txtPassword = findViewById(R.id.txtPassword)
        progressBar = findViewById(R.id.progressBar)
        auth = FirebaseAuth.getInstance()
    }


    object ServiceBuilder {
        private val client = OkHttpClient.Builder().build()

        private val retrofit = Retrofit.Builder()
            .baseUrl("https://serveless.proximateapps-services.com.mx")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        fun <T> buildService(service: Class<T>): T {
            return retrofit.create(service)
        }
    }

    fun sendPostRequest(view: View) {
        addDummyUser()
    }

    fun addDummyUser() {
        val user: String = txtUser.text.toString()
        val password: String = txtPassword.text.toString()

        val apiService = RestApiService()
        val Usuario = UserInfo(
            user = user,
            password = password
        )

        // Create JSON using JSONObject
        val jsonObject = JSONObject()
        jsonObject.put("user", user)
        jsonObject.put("password", password)

        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()

        apiService.addUser(Usuario) {
            if (it != null) {
                action(it.dataUser.userToken)
            } else {
                print("Error registering new user")
            }
        }
    }

    private fun action(tokenUsuario: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("token", tokenUsuario);
        startActivity(intent)
    }
}