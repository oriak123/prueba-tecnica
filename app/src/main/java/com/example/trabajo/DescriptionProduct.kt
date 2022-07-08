package com.example.trabajo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.trabajo.Product
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_description_product.*
import kotlinx.android.synthetic.main.activity_description_product.photoView


class DescriptionProduct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description_product)


        if (intent.extras != null) {
            Glide.with(this).load(intent.getStringExtra("imageUrl")).into(photoView)

        }

        if (intent.extras != null) {

            val descrip: Intent = intent
            var mostrarDescr = descrip.getStringExtra("descripUrl")
            descriptionAmpliada.text = "$mostrarDescr"
        }
    }
}
