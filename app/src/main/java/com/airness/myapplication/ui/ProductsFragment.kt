package com.airness.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.airness.myapplication.R
import com.airness.myapplication.databinding.FragmentProductsBinding
import com.airness.myapplication.viewmodel.MeubleViewModel

class ProductsFragment : Fragment() {
    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MeubleViewModel
    private lateinit var adapter: MeubleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[MeubleViewModel::class.java]

        adapter = MeubleAdapter(
            listOf(),
            onProductClick = { meuble ->
                val action = ProductsFragmentDirections.actionProductsFragmentToDetailFragment(meuble.id)
                findNavController().navigate(action)
            },
            onAddToCartClick = { meuble ->
                // Handle add to cart
            }
        )

        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.adapter = adapter
        viewModel.meubles.observe(viewLifecycleOwner) { meubles ->
            adapter.updateData(meubles)
        }

        setupSpinner()
        return binding.root
    }

    private fun setupSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.sort_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerSort.adapter = adapter
        }

        binding.spinnerSort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when (position) {
                    0 -> adapter.sortByName()
                    1 -> adapter.sortByPrice()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonToHome.setOnClickListener {
            findNavController().navigate(R.id.action_productsFragment_to_homeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
