package com.example.trabajo.adapter

import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.example.trabajo.Product
import com.example.trabajo.R
import com.example.trabajo.databinding.RecyclerBinding
import kotlinx.android.synthetic.main.recycler.view.*

class listViewHolder(
    private val binding: RecyclerBinding,
    private val itemClickListener: listAdapter.OnProductClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun render(itemProducto: Product) {

        itemView.setOnClickListener(){itemClickListener.onItemClickListener(itemProducto.longDescription, itemProducto.image, itemProducto.title)}
        //itemView.imageProduct.setOnClickListener(){itemClickListener.onImageClickListener(itemProducto.longDescription, image = "")}
        binding.Titulo.text = itemProducto.title
       // Log.d("usuario", itemProducto.image.toString())
        binding.imageProduct.loadUrl(itemProducto.image)
        Glide.with(binding.imageProduct).load(itemProducto.image).centerCrop()
            .error(R.drawable.microncon).into(binding.imageProduct)
    }

    fun ImageView.loadUrl(url: String) {

        val imageLoader = ImageLoader.Builder(context)
            .components {
                add(SvgDecoder.Factory())
            }
            .build()

        val request = ImageRequest.Builder(this.context)
            .crossfade(true)
            .crossfade(200)
            .data(url).error(R.drawable.biome2)
            .target(this)
            .build()

        imageLoader.enqueue(request)
    }
}