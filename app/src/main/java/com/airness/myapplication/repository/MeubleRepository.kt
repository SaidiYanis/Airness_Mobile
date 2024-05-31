package com.airness.myapplication.repository

import com.airness.myapplication.entity.Meuble

class MeubleRepository {

    fun getMeubles(): List<Meuble> {
        // Simule une liste de meubles
        return listOf(
            Meuble(1, "Table", "Une belle table en bois","Commode", 120.0, "url_image_table"),
            Meuble(2, "Chaise", "Une chaise confortable","Lit", 45.0, "url_image_chaise")
            // Ajoutez plus de meubles ici
        )
    }
}
