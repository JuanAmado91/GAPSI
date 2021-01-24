package com.example.gapsi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gapsi.R
import com.example.gapsi.model.response.DetailProduct
import com.squareup.picasso.Picasso

class CatalogAdapter : RecyclerView.Adapter<CatalogAdapter.ViewHolder>() {
    var detailProduct: List<DetailProduct>  = ArrayList()
    lateinit var context: Context

    fun catalogAdapter(productosCatalog : List<DetailProduct>, context: Context){
        this.detailProduct = productosCatalog
        this.context = context
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = detailProduct[position]
        holder.bind(item, context)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.adapter_catalog, parent, false))
    }
    override fun getItemCount(): Int {
        return detailProduct.size
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById(R.id.tvSuperhero) as TextView
        val realName = view.findViewById(R.id.tvRealName) as TextView
        val publisher = view.findViewById(R.id.tvPublisher) as TextView
        val avatar = view.findViewById(R.id.ivAvatar) as ImageView
        fun bind(product:DetailProduct, context: Context){
            name.text = product.title
            realName.text = product.rating
            publisher.text = product.price
            avatar.loadUrl(product.image)
            itemView.setOnClickListener{
                Toast.makeText(context, product._id, Toast.LENGTH_SHORT).show()
            }
        }
        fun ImageView.loadUrl(url: String) {
            Picasso.with(context).load(url).into(this)
        }
    }
}