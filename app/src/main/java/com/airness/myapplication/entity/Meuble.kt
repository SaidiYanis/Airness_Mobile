package com.airness.myapplication.entity

data class Meuble(
    val id: Int,
    val name: String,
    val description: String,
    val type: String,
    val price: Double,
    val imageUrl: String,
    val color: String,
    val material: String
)
