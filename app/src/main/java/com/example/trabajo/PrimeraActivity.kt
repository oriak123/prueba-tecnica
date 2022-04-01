package com.example.trabajo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class PrimeraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.primera_activity)
    }
    fun siguiente(view: View){

        startActivity(Intent(this,SplashActivity::class.java))

    }
}