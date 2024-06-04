package com.airness.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NavigationViewModel : ViewModel() {
    private val _navigationStack = MutableLiveData<MutableList<String>>(mutableListOf())
    val navigationStack: LiveData<MutableList<String>> get() = _navigationStack

    fun addPage(page: String) {
        _navigationStack.value?.add(page)
    }

    fun removePage(): String? {
        return _navigationStack.value?.removeAt(_navigationStack.value!!.size - 1)
    }
}
