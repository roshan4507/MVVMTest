package com.vicks.mvvmtest.products.presentation


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.vicks.mvvmtest.core.utils.NetworkResult
import com.vicks.mvvmtest.getOrAwaitValue
import com.vicks.mvvmtest.products.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ProductViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: ProductRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `test get products returns empty list`(){
        runTest {
            Mockito.`when`(repository.getProducts()).thenReturn(NetworkResult.Success(emptyList()))

            val sut = ProductViewModel(repository)
            sut.getProducts()
            testDispatcher.scheduler.advanceUntilIdle()
            val result = sut.products.getOrAwaitValue()
            Assert.assertEquals(0,result.data!!.size)
        }

    }

    @Test
    fun `test get products return error`(){
        runTest {
            Mockito.`when`(repository.getProducts()).thenReturn(NetworkResult.Error("Something went wrong"))

            val sut = ProductViewModel(repository)
            sut.getProducts()
            testDispatcher.scheduler.advanceUntilIdle()
            val result = sut.products.getOrAwaitValue()
            Assert.assertEquals(true, result is NetworkResult.Error)
            Assert.assertEquals("Something went wrong",result.message)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}