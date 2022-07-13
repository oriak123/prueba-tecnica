package com.example.trabajo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.example.trabajo.databinding.PrimeraActivityBinding
import com.google.firebase.auth.FirebaseAuth
import okhttp3.OkHttpClient
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginActivity : AppCompatActivity() {

    private lateinit var txtUser: EditText
    private lateinit var txtPassword: EditText
    private lateinit var progressBar: ProgressBar
   // private lateinit var auth: FirebaseAuth
    private lateinit var binding: PrimeraActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.primera_activity)
        binding = PrimeraActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        txtUser = findViewById(R.id.txtUser)
        txtPassword = findViewById(R.id.txtPassword)
        progressBar = findViewById(R.id.progressBar)
       // auth = FirebaseAuth.getInstance()

        //He agregado desde aqui
        //val sp = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

        //checkLogin(sp)

        //binding.btnLogin.setOnClickListener { addDummyUser(sp) }

        //
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
    //(sp:SharedPreferences
     fun addDummyUser() {
        //
        val user = binding.txtUser.editableText?.toString()
        val password = binding.txtPassword.editableText?.toString()
       // val user: String = txtUser.text.toString()
        //val password: String = txtPassword.text.toString()
        //
        /*if (user!!.isNotEmpty() && password!!.isNotEmpty()) {
            val checkBox = binding.checkBox
            if (checkBox.isChecked) {
                with(sp.edit()) {
                    putString("email", user)
                    putString("clave", password)
                    putString("active", "true")
                    putString("remember", "true")
                    apply()
                }
            } else {
                with(sp.edit()) {
                    putString("active", "true")
                    putString("remember", "false")
                    apply()
                    }
                }
            action("token")
                else {
                    Toast.makeText(this, "Intentalo nuevamente", Toast.LENGTH_SHORT).show()
                }
            }*/

            //
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
                    //Log.d("hola", it.dataUser.userToken.toString())
                    action(it.dataUser.userToken)
                } else {
                    print("Error registering new user")
                }

                //if user
            }
        }

        fun action(tokenUsuario: String) {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("token", tokenUsuario);
            startActivity(intent)
            finish()
        }
    }

   /* private fun checkLogin(sp: SharedPreferences, tokenUsuario: String) {

        if (sp.getString("active", "") == "true") {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("token", tokenUsuario);
            startActivity(intent)
            finish()

        } else {
            if (sp.getString("remember", "") == "true") {
                binding.txtUser.editableText?.setText(sp.getString("email", ""))
                binding.txtPassword.editableText?.setText(sp.getString("clave", ""))
            }
        }
    }
}*/