package com.example.trabajo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trabajo.adapter.listAdapter
import com.example.trabajo.databinding.AppBarMainBinding
import kotlinx.android.synthetic.main.recycler.*
import org.json.JSONObject



class RecyclerMain : AppCompatActivity(), listAdapter.OnProductClickListener {

    private lateinit var binding: AppBarMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AppBarMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val valor:String? = intent.getStringExtra("token")

        val apiService = RestApiService()

        val token = tokenGener(
            userToken = valor.toString()
        )
        binding.Recycler.layoutManager = LinearLayoutManager(this)

        apiService.getProducts(token) {
            //   Log.d( "hola",it?.data?.products.toString())
            binding.Recycler.adapter = it!!.data.products.let { listAdapter(it!!, this) }
        }
    }

    private fun addDummyUser() {
            //

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
                user = "1075258635",
                password = "testProximate"
            )
            apiService.addUser(Usuario) {
                if (it != null) {
                    Log.d("hola", it.dataUser.userToken.toString())
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
    fun volver_productos(view: View) {

        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onItemClickListener(longDescription: String, image: String) {

        val intent = Intent(this, DescriptionProduct::class.java)
        intent.putExtra("descripUrl", longDescription)
        intent.putExtra("imageUrl", image)
        startActivity(intent)

    }

    override fun onImageClickListener(longDescription: String, image: String) {
        val intent = Intent(this, DescriptionProduct::class.java)
        intent.putExtra("descripUrl", longDescription)
        intent.putExtra("imageUrl", image)
        startActivity(intent)

    }
}