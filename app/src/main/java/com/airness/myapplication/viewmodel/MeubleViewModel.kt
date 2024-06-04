package com.airness.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airness.myapplication.model.Meuble
import com.airness.myapplication.repository.MeubleRepository

class MeubleViewModel : ViewModel() {
    private val repository = MeubleRepository()
    private val _meubles = MutableLiveData<List<Meuble>>()
    val meubles: LiveData<List<Meuble>> get() = _meubles

    init {
        fetchMeubles()
    }

    private fun fetchMeubles() {
        _meubles.value = repository.getMeubles()
    }
}
