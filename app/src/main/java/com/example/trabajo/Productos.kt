package com.example.trabajo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Productos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)
    }

    fun microcontroladores (view: View){

        startActivity(Intent(this,Product1()::class.java))

    }fun microcontroladores2 (view: View){

        startActivity(Intent(this,Product1()::class.java))

    }fun robots (view: View){

        startActivity(Intent(this,Product2()::class.java))

    }fun robots2 (view: View){

        startActivity(Intent(this,Product2()::class.java))

    }fun biomecanica (view: View){

        startActivity(Intent(this,Product3()::class.java))

    }fun biomecanica2 (view: View){

        startActivity(Intent(this,Product3()::class.java))

    }fun volverinicio (view: View){

        startActivity(Intent(this,MainActivity()::class.java))

    }
}