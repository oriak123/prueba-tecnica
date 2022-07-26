package com.example.trabajo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trabajo.GlobalTokenApplication.Companion.prefs
import com.example.trabajo.databinding.PrimeraActivityBinding
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginActivity : AppCompatActivity() {

    private lateinit var txtUser: EditText
    private lateinit var txtPassword: EditText
    private lateinit var progressBar: ProgressBar


    private lateinit var binding: PrimeraActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PrimeraActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        txtUser = findViewById(R.id.txtUser)
        txtPassword = findViewById(R.id.txtPassword)
        progressBar = findViewById(R.id.progressBar)

        binding.btnLogin.setOnClickListener { addDummyUser() }


        autocompletarUsuario()
        credencial()
    }

    private fun credencial() {
        Log.d("123", prefs.getUser().toString())
        if ((prefs.getUser().isNotEmpty() && prefs.getPassw().isNotEmpty()) && prefs.getToken()
                .isNotEmpty()
        ) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    private fun autocompletarUsuario() {

        val usuario = prefs.getUser()
        val contraseña = prefs.getPassw()

        if (usuario.isNotEmpty() && contraseña.isNotEmpty()) {

            binding.txtUser.setText(usuario)
            binding.txtPassword.setText(contraseña)
        }
    }

    object ServiceBuilder {

        private val client = OkHttpClient.Builder().build()

        private val retrofit = Retrofit.Builder()
            .baseUrl("https://serveless.proximateapps-services.com.mx")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        fun <T> buildService(service: Class<T>): T { // clase generica
            return retrofit.create(service)  // retrofit por defecto nos pide interfaz en el metodo create, y esa interfaz es Apiservice
        }
    }

    fun addDummyUser() {

        progressBar.visibility = View.VISIBLE

        val user: String = txtUser.text.toString()
        val password: String = txtPassword.text.toString()

        if (user.isNotEmpty() && password.isNotEmpty()) {

            val apiService = RestApiService()
            val Usuario = UserInfo(
                user = user,
                password = password
            )

            apiService.addUser(Usuario) { usuarioRespuesta ->

                Log.d("usuresp", usuarioRespuesta.toString())

                if (usuarioRespuesta != null && usuarioRespuesta.status != false) {


                    usuarioRespuesta!!.dataUser!!.name?.let { action2(it) }

                    Log.d("hola", binding.checkBox.isChecked.toString())
                    prefs.saveGuardado(binding.checkBox.isChecked)
                    prefs.saveUser(binding.txtUser.text.toString())
                    prefs.savePassw(binding.txtPassword.text.toString())

                    usuarioRespuesta!!.dataUser!!.userToken?.let { action(it) }
                } else {

                    progressBar.visibility = View.GONE
                    Toast.makeText(
                        this, "Por favor ingrese los datos correctos", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } else {

            Toast.makeText(
                this, "Por favor ingrese los datos correctos", Toast.LENGTH_SHORT
            ).show()
        }
    }


    private fun action(tokenUsuario: String) {

        prefs.saveToken(tokenUsuario)
        Log.d("Otracosa", prefs.getToken())
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun action2(saludo: String) {
        prefs.saveSaludo(saludo)
        Log.d("OtroName", prefs.getSaludo())
    }
}




