package com.airness.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.airness.myapplication.databinding.FragmentHomeBinding
import com.airness.myapplication.viewmodel.MeubleViewModel
import com.bumptech.glide.Glide

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MeubleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Charger l'image avec Glide
        Glide.with(this)
            .load("URL_DE_VOTRE_IMAGE") // Remplacez par l'URL de votre image
            .into(binding.imageViewMeuble)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = MeubleAdapter()
        recyclerView.adapter = adapter

        viewModel.meubles.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        viewModel.fetchMeubles()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
