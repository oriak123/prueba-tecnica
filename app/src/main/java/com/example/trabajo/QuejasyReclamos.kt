package com.example.trabajo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.trabajo.databinding.ActivityQuejasyReclamosBinding

class QuejasyReclamos : AppCompatActivity() {

    lateinit var binding: ActivityQuejasyReclamosBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityQuejasyReclamosBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun volverinicio(view: View) {
        val intent2 = Intent(this, MainActivity::class.java)
        startActivity(intent2)
    }

}

