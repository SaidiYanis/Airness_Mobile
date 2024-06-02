package com.airness.myapplication.repository

import com.airness.myapplication.entity.Meuble

class MeubleRepository {

    fun getMeubles(): List<Meuble> {
        // Simule une liste de meubles
        return listOf(
            Meuble(1, "Table Noir Thormund", "Une belle table en bois massif noir avec des pieds en acier, idéale pour ajouter une touche de sophistication à votre salle à manger.", "table", 199.99, "table", "black", "acier"),
            Meuble(2, "Chaise Noire Nordik", "Une chaise confortable en bois noir, alliant un design épuré à un confort optimal, parfaite pour une salle à manger moderne.", "chaise", 49.99, "chair", "black", "bois"),
            Meuble(3, "Sofa Blanc Eklund", "Un sofa confortable et moderne en tissu blanc avec une structure en acier, idéal pour un salon contemporain et élégant.", "canape", 399.99, "sofa", "blanc", "acier"),
            Meuble(4, "Chaise Orange Lulea", "Chaise confortable de couleur orange vif, fabriquée en bois robuste, parfaite pour ajouter une touche de couleur et de style à votre intérieur.", "chaise", 99.99, "orangechair", "orange", "bois"),
            Meuble(5, "Meuble Télé Svea", "Meuble de télévision en bois marron au design scandinave, offrant une solution élégante et fonctionnelle pour votre salon.", "meuble", 599.99, "furnituretv", "marron", "bois"),
            Meuble(6, "Meuble Box Eskild", "Meuble box en forme d'escalier, en plastique gris, offrant une solution de rangement pratique et moderne pour n'importe quelle pièce.", "meuble", 399.99, "box", "gris", "plastique"),
            Meuble(7, "Meuble à Chaussures Valborg", "Un magnifique meuble en bois noir pour ranger vos chaussures, offrant à la fois fonctionnalité et élégance à votre entrée.", "meuble", 399.99, "shoesbox", "noir", "bois")
        )
    }

    fun getMeublesSortedByPrice(): List<Meuble> {
        return getMeubles().sortedBy { it.price }
    }

    fun getMeublesSortedByName(): List<Meuble> {
        return getMeubles().sortedBy { it.name }
    }
}
