package com.example.trabajo

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.trabajo.GlobalTokenApplication.Companion.prefs
import com.example.trabajo.databinding.SegundaActivityBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_description_product.*
import kotlinx.android.synthetic.main.mostar_productos.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: SegundaActivityBinding
    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = SegundaActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("Otracosa", GlobalTokenApplication.prefs.getToken())

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)

        toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        ponerSaludo()

    }


    private fun ponerSaludo (){
        val traerName = prefs.getSaludo()
        textView2.text = "!BIENVENIDO A APP LEGEND $traerName!"
        }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_item_one -> {
                val intent1 = Intent(this, MainActivity::class.java)
                startActivity(intent1)
            }

            R.id.nav_item_two -> {
                val intent2 = Intent(this, RecyclerMain::class.java)

                startActivity(intent2)
            }


            R.id.nav_item_three -> {
                startActivity(Intent(this, QuejasyReclamos::class.java))
            }

            R.id.nav_item_four -> {
                val sp = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
                with(sp.edit()) {
                    putBoolean("active", false)
                    apply()
                }
                Log.d("ole",prefs.getGuardado().toString())
                if(!prefs.getGuardado()){
                    prefs.saveUser("")
                    prefs.savePassw("")
                }
                if(prefs.getToken().isNotEmpty()){
                    prefs.saveToken("")
                }
                val intent2 = Intent(this, LoginActivity::class.java)
                startActivity(intent2)
            }
        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        /* if (toggle.onOptionsItemSelected(item)) {
             return true
         }*/
        return super.onOptionsItemSelected(item)
    }
}

