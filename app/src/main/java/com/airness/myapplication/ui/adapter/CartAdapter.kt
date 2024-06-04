package com.airness.myapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airness.myapplication.databinding.ItemCartBinding
import com.airness.myapplication.model.CartItem

class CartAdapter(
    private val onRemoveClick: (CartItem) -> Unit,
    private val onAddClick: (CartItem) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private var cartItems: List<CartItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItems[position]
        holder.bind(cartItem)
        holder.binding.buttonRemove.setOnClickListener { onRemoveClick(cartItem) }
        holder.binding.buttonAdd.setOnClickListener { onAddClick(cartItem) }
    }

    override fun getItemCount(): Int = cartItems.size

    fun submitList(newCartItems: List<CartItem>) {
        cartItems = newCartItems
        notifyDataSetChanged()
    }

    class CartViewHolder(val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cartItem: CartItem) {
            binding.textViewItemName.text = cartItem.meuble.name
            binding.textViewItemQuantity.text = cartItem.quantity.toString()
            binding.textViewItemPrice.text = "${cartItem.meuble.price} €"
        }
    }
}
