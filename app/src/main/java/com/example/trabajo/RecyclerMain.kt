package com.example.trabajo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trabajo.GlobalTokenApplication.Companion.prefs
import com.example.trabajo.adapter.listAdapter
import com.example.trabajo.databinding.AppBarMainBinding


class RecyclerMain : AppCompatActivity(), listAdapter.OnProductClickListener {

    private lateinit var binding: AppBarMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AppBarMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Log.d("Otracosa", GlobalTokenApplication.prefs.getToken())

        val valor = prefs.getToken()

        val apiService = RestApiService()

        val token = tokenGener(
            userToken = valor.toString()
        )
        binding.Recycler.layoutManager = LinearLayoutManager(this)

        apiService.getProducts(token) {
            //   Log.d( "hola",it?.data?.products.toString())
            binding.Recycler.adapter =
                it!!.data.products.let { listAdapter(it!!, this) }// poner try catch
        }


    }

    override fun onItemClickListener(longDescription: String, image: String, title: String) {

        val intent = Intent(this, DescriptionProduct::class.java)
        intent.putExtra("descripUrl", longDescription)
        intent.putExtra("imageUrl", image)
        intent.putExtra("titulo", title.toUpperCase())
        startActivity(intent)

    }
}