package com.example.trabajo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class QuejasyReclamos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quejasy_reclamos)
    }
    fun volverinicio (view: View){
        startActivity(Intent (this, MainActivity()::class.java))

    }
}