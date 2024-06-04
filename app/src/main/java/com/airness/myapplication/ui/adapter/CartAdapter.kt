package com.airness.myapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airness.myapplication.databinding.ItemCartBinding
import com.airness.myapplication.model.CartItem
import com.bumptech.glide.Glide

class CartAdapter(
    private val onRemoveClick: (CartItem) -> Unit,
    private val onAddClick: (CartItem) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private var items: List<CartItem> = listOf()

    fun submitList(newItems: List<CartItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.binding.buttonRemove.setOnClickListener { onRemoveClick(item) }
        holder.binding.buttonAdd.setOnClickListener { onAddClick(item) }
    }

    override fun getItemCount(): Int = items.size

    class CartViewHolder(val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CartItem) {
            binding.textViewItemName.text = item.meuble.name
            binding.textViewItemQuantity.text = item.quantity.toString()
            binding.textViewItemPrice.text = "${item.meuble.price} €"
            Glide.with(binding.root.context)
                .load(binding.root.context.resources.getIdentifier(item.meuble.imageUrl, "drawable", binding.root.context.packageName))
                .into(binding.imageViewItem)
        }
    }
}
