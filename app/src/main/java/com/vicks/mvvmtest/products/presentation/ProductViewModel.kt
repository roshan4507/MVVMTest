package com.vicks.mvvmtest.products.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vicks.mvvmtest.core.utils.NetworkResult
import com.vicks.mvvmtest.products.data.models.Product
import com.vicks.mvvmtest.products.domain.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository):ViewModel() {

    private val _products = MutableLiveData<NetworkResult<List<Product>>>()
    val products : LiveData<NetworkResult<List<Product>>>
        get() = _products

    fun getProducts(){
        viewModelScope.launch {
            val result = repository.getProducts()
            _products.postValue(result)
        }
    }
}