package com.airness.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.airness.myapplication.R
import com.airness.myapplication.databinding.FragmentCartBinding
import com.airness.myapplication.viewmodel.CartViewModel
import com.airness.myapplication.ui.adapter.CartAdapter

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private lateinit var cartViewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        cartViewModel = ViewModelProvider(requireActivity())[CartViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CartAdapter(
            onRemoveClick = { cartItem ->
                cartViewModel.removeFromCart(cartItem.meuble)
            },
            onAddClick = { cartItem ->
                cartViewModel.addToCart(cartItem.meuble)
            }
        )

        binding.recyclerViewCart.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewCart.adapter = adapter

        cartViewModel.cartItems.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
            binding.textViewTotalPrice.text = getString(R.string.total_price, cartViewModel.getTotalPrice().toString())
        }

        binding.buttonCheckout.setOnClickListener {
            findNavController().navigate(R.id.action_nav_cart_to_checkoutFragment)
        }

        binding.buttonReturnToHome.setOnClickListener {
            findNavController().navigate(R.id.action_nav_cart_to_homeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
