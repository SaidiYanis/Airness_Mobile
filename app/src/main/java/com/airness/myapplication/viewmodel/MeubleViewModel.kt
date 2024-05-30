package com.airness.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airness.myapplication.entity.Meuble
import com.airness.myapplication.repository.MeubleRepository

class MeubleViewModel : ViewModel() {

    private val repository = MeubleRepository()
    val meubles = MutableLiveData<List<Meuble>>()

    fun fetchMeubles() {
        meubles.value = repository.getMeubles()
    }
}
