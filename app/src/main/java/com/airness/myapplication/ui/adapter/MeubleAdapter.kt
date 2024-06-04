package com.airness.myapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airness.myapplication.databinding.ItemMeubleBinding
import com.airness.myapplication.model.Meuble
import com.bumptech.glide.Glide

class MeubleAdapter(
    private var meubles: List<Meuble>,
    private val onProductClick: (Meuble) -> Unit,
    private val onAddToCartClick: (Meuble) -> Unit,
    private val onViewProductClick: (Meuble) -> Unit
) : RecyclerView.Adapter<MeubleAdapter.MeubleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeubleViewHolder {
        val binding = ItemMeubleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MeubleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MeubleViewHolder, position: Int) {
        val meuble = meubles[position]
        holder.bind(meuble)
        holder.binding.buttonAddToCart.setOnClickListener { onAddToCartClick(meuble) }
        holder.binding.buttonViewProduct.setOnClickListener { onViewProductClick(meuble) }
    }

    override fun getItemCount(): Int = meubles.size

    fun updateData(newMeubles: List<Meuble>) {
        meubles = newMeubles
        notifyDataSetChanged()
    }

    fun sortBy(selector: (Meuble) -> Comparable<*>) {
        meubles = meubles.sortedWith(compareBy(selector))
        notifyDataSetChanged()
    }

    class MeubleViewHolder(val binding: ItemMeubleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(meuble: Meuble) {
            binding.textViewProductName.text = meuble.name
            binding.textViewProductPrice.text = "${meuble.price} €"
            Glide.with(binding.root.context)
                .load(binding.root.context.resources.getIdentifier(meuble.imageUrl, "drawable", binding.root.context.packageName))
                .into(binding.imageViewProduct)
        }
    }
}
