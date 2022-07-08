package com.example.trabajo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.trabajo.databinding.SegundaActivityBinding

class Product3 : AppCompatActivity() {

    private lateinit var binding: SegundaActivityBinding
    private lateinit var drawer: DrawerLayout
    private lateinit var texto: TextView
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SegundaActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun volverinicio (view: View){

        startActivity(Intent(this,MainActivity()::class.java))

    }
}