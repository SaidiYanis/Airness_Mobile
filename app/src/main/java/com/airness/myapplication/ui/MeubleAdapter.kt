package com.airness.myapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airness.myapplication.databinding.ItemMeubleBinding
import com.airness.myapplication.entity.Meuble

class MeubleAdapter :
    ListAdapter<Meuble, MeubleAdapter.MeubleViewHolder>(MeubleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeubleViewHolder {
        val binding = ItemMeubleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MeubleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MeubleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MeubleViewHolder(private val binding: ItemMeubleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(meuble: Meuble) {
            binding.apply {
                textViewNom.text = meuble.nom
                textViewDescription.text = meuble.description
                textViewPrix.text = "${meuble.prix} €"
                // Load image using your preferred image loading library
            }
        }
    }

    class MeubleDiffCallback : DiffUtil.ItemCallback<Meuble>() {
        override fun areItemsTheSame(oldItem: Meuble, newItem: Meuble): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Meuble, newItem: Meuble): Boolean {
            return oldItem == newItem
        }
    }
}
