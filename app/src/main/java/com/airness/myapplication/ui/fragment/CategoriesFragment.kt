package com.airness.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.airness.myapplication.databinding.FragmentCategoriesBinding
import com.airness.myapplication.viewmodel.CategoryViewModel
import com.airness.myapplication.R
import com.airness.myapplication.viewmodel.NavigationViewModel
import com.airness.myapplication.ui.adapter.CategoryAdapter

class CategoriesFragment : Fragment() {
    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CategoryViewModel
    private lateinit var navigationViewModel: NavigationViewModel
    private lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        navigationViewModel = ViewModelProvider(requireActivity())[NavigationViewModel::class.java]

        adapter = CategoryAdapter(listOf()) { category ->
            navigationViewModel.addPage("categories")
            val action = CategoriesFragmentDirections.actionCategoriesFragmentToProductsFragment(category.id, "categories")
            findNavController().navigate(action)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
        viewModel.categories.observe(viewLifecycleOwner) { categories ->
            adapter.updateData(categories)
        }

        viewModel.fetchCategories()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}