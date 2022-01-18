package com.finite.ipayapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.finite.ipayapp.R

class CartAdapter(
    private val quantity: MutableList<String>,
    private val name: MutableList<String>,
    private val price: MutableList<String>) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_single_item,parent,false)

        return ViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemCount.text = quantity[position]
        holder.itemName.text = name[position]
        holder.itemPrice.text = price[position]
    }

    override fun getItemCount(): Int {
        return quantity.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var itemCount : TextView
        var itemName : TextView
        var itemPrice : TextView

        init {
            itemCount = view.findViewById(R.id.item_count)
            itemName = view.findViewById(R.id.item_name)
            itemPrice = view.findViewById(R.id.item_price)
        }
    }
}