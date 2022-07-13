package com.example.trabajo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.trabajo.databinding.ActivityDescriptionProductBinding
import kotlinx.android.synthetic.main.activity_description_product.*




class ImageDetail : AppCompatActivity() {

    lateinit var binding: ActivityDescriptionProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_description_product)
        binding= ActivityDescriptionProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.extras != null) {
            Glide.with(this).load(intent.getStringExtra("imageUrl")).into(fotoAmpliada)

        }
        if (intent.extras != null) {

            val descrip: Intent = intent
            var mostrarDescr = descrip.getStringExtra("descripUrl")
            descriptionAmpliada.text = "$mostrarDescr"
        }

    }
}



