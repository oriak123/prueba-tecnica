package com.example.trabajo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        splash()
    }
    fun splash() {
        val spl=Intent(this,SegundaActivity::class.java)
        Handler().postDelayed({ startActivity(spl)},500)
    }


}