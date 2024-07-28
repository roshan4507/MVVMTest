package com.vicks.mvvmtest.products.data.api

import com.vicks.mvvmtest.products.data.models.Product
import retrofit2.Response
import retrofit2.http.GET

interface ProductAPI {

    @GET("/products")
    suspend fun getProducts():Response<List<Product>>
}