package com.airness.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airness.myapplication.model.Category
import com.airness.myapplication.repository.CategoryRepository

class CategoryViewModel : ViewModel() {
    private val repository = CategoryRepository()
    val categories = MutableLiveData<List<Category>>()

    init {
        fetchCategories()
    }

    fun fetchCategories() {
        categories.value = repository.getCategories()
    }
}
