package com.example.trabajo.adapter

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.example.trabajo.Product
import com.example.trabajo.R
import com.example.trabajo.databinding.RecyclerBinding

class listViewHolder(
    private val binding: RecyclerBinding,
    private val itemClickListener: listAdapter.OnProductClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun render(itemProducto: Product) {

        itemView.setOnClickListener(){itemClickListener.onItemClickListener(itemProducto.longDescription, itemProducto.image, itemProducto.title)}
        binding.Titulo.text = itemProducto.title.toUpperCase()
        binding.imageProduct.loadUrl(itemProducto.image)
        Glide.with(binding.imageProduct).load(itemProducto.image).centerCrop().error(R.drawable.microncon).into(binding.imageProduct)
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
            .data(url).error(R.drawable.microncon)
            .target(this)
            .build()

        imageLoader.enqueue(request)
    }
}