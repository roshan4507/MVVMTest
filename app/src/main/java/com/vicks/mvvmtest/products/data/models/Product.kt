package com.vicks.mvvmtest.products.data.models

data class Product(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val title: String
):java.io.Serializable
