package com.airness.myapplication.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.airness.myapplication.model.Meuble
import com.airness.myapplication.ui.adapter.MeubleAdapter
import com.airness.myapplication.viewmodel.MeubleViewModel
import com.airness.myapplication.viewmodel.NavigationViewModel

class ProductsFragment : Fragment() {
    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MeubleViewModel
    private lateinit var navigationViewModel: NavigationViewModel
    private lateinit var adapter: MeubleAdapter
    private var categoryId: Int = -1
    private var allMeubles: List<Meuble> = listOf() // To keep all meubles for search filtering

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[MeubleViewModel::class.java]
        navigationViewModel = ViewModelProvider(requireActivity())[NavigationViewModel::class.java]

        categoryId = arguments?.getInt("categoryId") ?: -1

        adapter = MeubleAdapter(
            listOf(),
            onProductClick = { meuble ->
                navigationViewModel.addPage("products")
                val action = ProductsFragmentDirections.actionProductsFragmentToDetailFragment(meuble.id, "products")
                findNavController().navigate(action)
            },
            onAddToCartClick = { meuble ->
                // Handle add to cart
            }
        )

        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.adapter = adapter
        viewModel.meubles.observe(viewLifecycleOwner) { meubles ->
            allMeubles = meubles
            val filteredMeubles = if (categoryId != -1) {
                meubles.filter { it.categoryId == categoryId }
            } else {
                meubles
            }
            adapter.updateData(filteredMeubles)
        }

        setupSpinner()
        setupSearch()
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

    private fun setupSearch() {
        binding.editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterMeubles(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterMeubles(query: String) {
        val filteredMeubles = if (categoryId != -1) {
            allMeubles.filter { it.categoryId == categoryId && it.name.contains(query, ignoreCase = true) }
        } else {
            allMeubles.filter { it.name.contains(query, ignoreCase = true) }
        }
        adapter.updateData(filteredMeubles)
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
