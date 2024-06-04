package com.airness.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airness.myapplication.model.CartItem
import com.airness.myapplication.model.Meuble

class CartViewModel : ViewModel() {

    private val _cartItems = MutableLiveData<MutableList<CartItem>>(mutableListOf())
    val cartItems: LiveData<MutableList<CartItem>> get() = _cartItems

    fun addToCart(meuble: Meuble) {
        val existingItem = _cartItems.value?.find { it.meuble.id == meuble.id }
        if (existingItem != null) {
            existingItem.quantity++
        } else {
            _cartItems.value?.add(CartItem(meuble, 1))
        }
        _cartItems.value = _cartItems.value
    }

    fun removeFromCart(meuble: Meuble) {
        val existingItem = _cartItems.value?.find { it.meuble.id == meuble.id }
        if (existingItem != null) {
            if (existingItem.quantity > 1) {
                existingItem.quantity--
            } else {
                _cartItems.value?.remove(existingItem)
            }
        }
        _cartItems.value = _cartItems.value
    }

    fun getTotalPrice(): Double {
        return _cartItems.value?.sumOf { it.meuble.price * it.quantity } ?: 0.0
    }
}
