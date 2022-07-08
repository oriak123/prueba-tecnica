package com.example.trabajo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Product2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product2)
    }
    fun volverinicio (view: View){

        startActivity(Intent(this,MainActivity()::class.java))

    }
}