package com.airness.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import com.airness.myapplication.R
import com.airness.myapplication.databinding.FragmentCheckoutBinding

class CheckoutFragment : Fragment() {

    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.checkBoxFreeShipping.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.checkBoxPaidShipping.isChecked = false
            }
        }

        binding.checkBoxPaidShipping.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.checkBoxFreeShipping.isChecked = false
            }
        }

        binding.buttonPlaceOrder.setOnClickListener {
            // Logique de commande
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
