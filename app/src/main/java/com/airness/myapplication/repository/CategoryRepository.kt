package com.airness.myapplication.repository

import com.airness.myapplication.model.Category

class CategoryRepository {

    fun getCategories(): List<Category> {
        // Simule une liste de catégories
        return listOf(
            Category(1, "Tables", "Découvrez notre collection de tables, alliant design élégant et fonctionnalité. Que vous cherchiez une table à manger en bois massif pour vos dîners en famille ou une table basse moderne pour votre salon, vous trouverez chez nous le meuble idéal pour sublimer votre intérieur.", "categorytable"),
            Category(2, "Chaises", "Nos chaises sont conçues pour offrir un confort optimal tout en ajoutant une touche de style à votre espace. Du design scandinave épuré aux couleurs vives qui apportent du dynamisme à votre pièce, explorez notre gamme variée pour trouver la chaise parfaite pour chaque occasion.", "categorychair"),
            Category(3, "Canapés", "Catégorie pour toutes sortes de canapés", "categorycouch"),
            Category(4, "Meubles", "Catégorie pour toutes sortes de meubles", "categorymeuble")
        )
    }
}
