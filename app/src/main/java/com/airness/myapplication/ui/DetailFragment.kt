package com.airness.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.airness.myapplication.databinding.FragmentDetailBinding
import com.airness.myapplication.viewmodel.MeubleViewModel
import com.bumptech.glide.Glide
import com.airness.myapplication.R


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MeubleViewModel
    private var meubleId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[MeubleViewModel::class.java]
        meubleId = arguments?.getInt("meubleId") ?: -1
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            }
        }

        binding.buttonToHomeFromDetail.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
