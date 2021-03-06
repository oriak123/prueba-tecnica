package com.example.trabajo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo.Product
import com.example.trabajo.databinding.RecyclerBinding

class listAdapter(
    private val listado: List<Product>,
    private val itemClickListener: OnProductClickListener
) : RecyclerView.Adapter<listViewHolder>() {

    interface OnProductClickListener {

        fun onItemClickListener(longDescription: String, image: String, title: String)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): listViewHolder {

        val binding = RecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return listViewHolder(binding,itemClickListener)

    }

    override fun onBindViewHolder(holder: listViewHolder, position: Int) {

        val item = listado[position]
        holder.render(item)

    }

    override fun getItemCount(): Int = listado.size

}