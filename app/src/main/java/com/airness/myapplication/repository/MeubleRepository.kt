package com.airness.myapplication.repository

import com.airness.myapplication.model.Meuble

class MeubleRepository {

    fun getMeubles(): List<Meuble> {
        return listOf(
            Meuble(1, "Thormund Table Noir", "Une belle table en bois massif noir avec des pieds en acier, idéale pour ajouter une touche de sophistication à votre salle à manger.", 1, 199.99, "table", "Noir", "Acier"),
            Meuble(2, "Nordik Chaise Noire", "Une chaise confortable en bois noir, alliant un design épuré à un confort optimal, parfaite pour une salle à manger moderne.", 2, 49.99, "chair", "Noir", "Bois"),
            Meuble(3, "Eklund Sofa Blanc", "Un sofa confortable et moderne en tissu blanc avec une structure en acier, idéal pour un salon contemporain et élégant.", 3, 399.99, "sofa", "Blanc", "Acier"),
            Meuble(4, "Vulea Chaise Orange", "Chaise confortable de couleur orange vif, fabriquée en bois robuste, parfaite pour ajouter une touche de couleur et de style à votre intérieur.", 2, 99.99, "orangechair", "Orange", "Bois"),
            Meuble(5, "Svea Meuble Télé", "Meuble de télévision en bois marron au design scandinave, offrant une solution élégante et fonctionnelle pour votre salon.", 4, 599.99, "furnituretv", "Marron", "Bois"),
            Meuble(6, "Eskild Meuble Box", "Meuble box en forme d'escalier, en plastique gris, offrant une solution de rangement pratique et moderne pour n'importe quelle pièce.", 4, 399.99, "box", "Gris", "Plastique"),
            Meuble(7, "Valborg Meuble à Chaussures ", "Un magnifique meuble en bois noir pour ranger vos chaussures, offrant à la fois fonctionnalité et élégance à votre entrée.", 4, 399.99, "shoesbox", "Noir", "Bois"),
            Meuble(8, "Vulea Chaise Orange", "Chaise confortable de couleur orange vif, fabriquée en bois robuste, parfaite pour ajouter une touche de couleur et de style à votre intérieur.", 2, 99.99, "orangechair", "Orange", "Bois"),
            Meuble(9, "Vulea Chaise Orange", "Chaise confortable de couleur orange vif, fabriquée en bois robuste, parfaite pour ajouter une touche de couleur et de style à votre intérieur.", 2, 99.99, "orangechair", "Orange", "Bois"),
            Meuble(10, "Vulea Chaise Orange", "Chaise confortable de couleur orange vif, fabriquée en bois robuste, parfaite pour ajouter une touche de couleur et de style à votre intérieur.", 2, 99.99, "orangechair", "Orange", "Bois"),
            Meuble(11, "Vulea Chaise Orange", "Chaise confortable de couleur orange vif, fabriquée en bois robuste, parfaite pour ajouter une touche de couleur et de style à votre intérieur.", 2, 99.99, "orangechair", "Orange", "Bois")

            )
    }

    fun getMeublesSortedByPrice(): List<Meuble> {
        return getMeubles().sortedBy { it.price }
    }

    fun getMeublesSortedByName(): List<Meuble> {
        return getMeubles().sortedBy { it.name }
    }
}
