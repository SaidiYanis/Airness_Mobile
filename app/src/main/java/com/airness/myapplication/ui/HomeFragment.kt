package com.airness.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.airness.myapplication.databinding.FragmentHomeBinding
import com.airness.myapplication.util.TopRoundedCornersTransformation
import com.bumptech.glide.Glide
import com.airness.myapplication.R

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Charger l'image avec Glide et arrondir les coins en haut
        Glide.with(this)
            .load(R.drawable.meuble) // Référence à l'image dans drawable
            .transform(TopRoundedCornersTransformation(30f)) // Modifier le rayon selon vos besoins
            .into(binding.imageViewMeuble)

        binding.buttonProducts.setOnClickListener {
            findNavController().navigate(R.id.nav_products)
        }

        binding.buttonCategories.setOnClickListener {
            findNavController().navigate(R.id.nav_categories)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
