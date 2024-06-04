package com.airness.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.airness.myapplication.databinding.FragmentDetailBinding
import com.airness.myapplication.viewmodel.MeubleViewModel
import com.airness.myapplication.viewmodel.NavigationViewModel
import com.bumptech.glide.Glide
import com.airness.myapplication.R
import com.airness.myapplication.ui.adapter.MeubleAdapter

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MeubleViewModel
    private lateinit var navigationViewModel: NavigationViewModel
    private var meubleId: Int = -1
    private lateinit var similarAdapter: MeubleAdapter
    private var returnTo: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[MeubleViewModel::class.java]
        navigationViewModel = ViewModelProvider(requireActivity())[NavigationViewModel::class.java]
        meubleId = arguments?.getInt("meubleId") ?: -1
        returnTo = arguments?.getString("returnTo")

        setupSimilarProductsRecyclerView()
        return binding.root
    }

    private fun setupSimilarProductsRecyclerView() {
        similarAdapter = MeubleAdapter(
            listOf(),
            onProductClick = { meuble ->
                val action = DetailFragmentDirections.actionDetailFragmentSelf(meuble.id, returnTo ?: "")
                findNavController().navigate(action)
            },
            onAddToCartClick = { meuble ->
                // Handle add to cart
            }
        )

        binding.recyclerViewSimilarProducts.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewSimilarProducts.adapter = similarAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigationViewModel.addPage("Detail")

        viewModel.meubles.observe(viewLifecycleOwner) { meubles ->
            val meuble = meubles.find { it.id == meubleId }
            meuble?.let {
                binding.textViewDetailName.text = it.name
                binding.textViewDetailPrice.text = "${it.price} €"
                binding.textViewDetailMaterial.text = it.material
                binding.textViewDetailColor.text = "Couleur : ${it.color}"
                binding.textViewDetailDescription.text = it.description
                Glide.with(requireContext())
                    .load(requireContext().resources.getIdentifier(it.imageUrl, "drawable", requireContext().packageName))
                    .into(binding.imageViewDetail)

                val similarProducts = meubles.filter { m -> m.categoryId == it.categoryId && m.id != it.id }
                similarAdapter.updateData(similarProducts)
            }
        }

        binding.buttonToHomeFromDetail.setOnClickListener {
            when (returnTo) {
                "products" -> findNavController().navigate(R.id.action_detailFragment_to_productsFragment)
                "categories" -> findNavController().navigate(R.id.action_detailFragment_to_categoriesFragment)
                else -> findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
