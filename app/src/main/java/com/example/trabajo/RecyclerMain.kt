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

        val valor = intent.getStringExtra("token")

        if (valor == null){
            intent.getStringExtra("token")
        }

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

    override fun onItemClickListener(longDescription: String, image: String, title:String) {

        val intent = Intent(this, DescriptionProduct::class.java)
        intent.putExtra("descripUrl", longDescription)
        intent.putExtra("imageUrl", image)
        intent.putExtra("titulo",title)
        startActivity(intent)

    }

    /*override fun onImageClickListener(longDescription: String, image: String) {
        val intent = Intent(this, DescriptionProduct::class.java)
        intent.putExtra("descripUrl", longDescription)
        intent.putExtra("imageUrl", image)
        startActivity(intent)

    }*/
}