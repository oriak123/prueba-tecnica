package com.example.trabajo

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.ListAdapter
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trabajo.adapter.listAdapter
import com.example.trabajo.databinding.AppBarMainBinding
import com.example.trabajo.databinding.SegundaActivityBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.recycler.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    listAdapter.OnProductClickListener {

    private lateinit var binding: SegundaActivityBinding
    private lateinit var drawer: DrawerLayout
    private lateinit var texto: TextView
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = SegundaActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val valor = intent.extras!!.getString("token")

        val apiService = RestApiService()

        val token = tokenGener(
            userToken = valor.toString()

        )

        binding.included.Recycler.layoutManager = LinearLayoutManager(this)

        apiService.getProducts(token) {
            binding.included.Recycler.adapter = it!!.data.products.let { listAdapter(it!!, this) }
        }


        /*val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_main)
         setSupportActionBar(toolbar)

         drawer = findViewById(R.id.drawer_layout)

         toggle = ActionBarDrawerToggle(
             this,
             drawer,
             toolbar,
             R.string.navigation_drawer_open,
             R.string.navigation_drawer_close
         )
         drawer.addDrawerListener(toggle)*/

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_item_one -> startActivity(Intent(this, MainActivity::class.java))
            R.id.nav_item_three -> startActivity(Intent(this, QuejasyReclamos::class.java))
            R.id.nav_item_four -> startActivity(Intent(this, LoginActivity::class.java))

        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        // toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        //toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        /* if (toggle.onOptionsItemSelected(item)) {
             return true
         }*/
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClickListener(longDescription: String, image: String) {

        val intent = Intent(this, DescriptionProduct::class.java)
        intent.putExtra("descripUrl", longDescription)
        intent.putExtra("imageUrl", image)
        startActivity(intent)

    }

    override fun onImageClickListener(longDescription: String, image: String) {
        val intent = Intent(this, DescriptionProduct::class.java)
        intent.putExtra("descripUrl", longDescription)
        intent.putExtra("imageUrl", image)
        startActivity(intent)

    }

}

