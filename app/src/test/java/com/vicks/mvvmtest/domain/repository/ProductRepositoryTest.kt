package com.vicks.mvvmtest.domain.repository

import com.vicks.mvvmtest.core.utils.NetworkResult
import com.vicks.mvvmtest.getOrAwaitValue
import com.vicks.mvvmtest.products.data.api.ProductAPI
import com.vicks.mvvmtest.products.data.models.Product
import com.vicks.mvvmtest.products.domain.repository.ProductRepository
import com.vicks.mvvmtest.products.presentation.ProductViewModel
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class ProductRepositoryTest {

    @Mock
    lateinit var productAPI: ProductAPI

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `get products return empty list`() = runTest {
        Mockito.`when`(productAPI.getProducts()).thenReturn(Response.success(emptyList()))
        val sut = ProductRepository(productAPI)
        val result = sut.getProducts()
        Assert.assertEquals(true, result is NetworkResult.Success)
        Assert.assertEquals(0, result.data!!.size)
    }

    @Test
    fun `get products return product list`() = runTest {

        val productList = listOf<Product>(
            Product("Category 1", "Desc 1", 1, "Img 1", 99.99, "Title 1"),
            Product("Category 2", "Desc 2", 2, "Img 2", 199.99, "Title 2")
        )

        Mockito.`when`(productAPI.getProducts()).thenReturn(Response.success(productList))
        val sut = ProductRepository(productAPI)
        val result = sut.getProducts()
        Assert.assertEquals(true, result is NetworkResult.Success)
        Assert.assertEquals(2, result.data!!.size)
        Assert.assertEquals("Title 1", result.data!![0].title)
    }

    @Test
    fun `get products return error`(){
        runTest {
            Mockito.`when`(productAPI.getProducts()).thenReturn(Response.error(401,"Something went wrong".toResponseBody()))

            val sut = ProductRepository(productAPI)
            val result = sut.getProducts()
            Assert.assertEquals("Something went wrong", result.message)
        }
    }
}