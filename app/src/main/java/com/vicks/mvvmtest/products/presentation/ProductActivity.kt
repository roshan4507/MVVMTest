package com.vicks.mvvmtest.products.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.vicks.mvvmtest.R
import com.vicks.mvvmtest.core.StoreApplication
import com.vicks.mvvmtest.core.utils.NetworkResult
import com.vicks.mvvmtest.databinding.ActivityProductBinding
import com.vicks.mvvmtest.products.data.models.Product

class ProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductBinding
    private lateinit var productViewModel: ProductViewModel
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product)
        val repository = (application as StoreApplication).productRepository
        productViewModel =
            ViewModelProvider(
                this,
                ProductViewModelFactory(repository)
            ).get(ProductViewModel::class.java)
        initRecyclerView()
        displayProducts()
    }

    private fun initRecyclerView() {
        adapter = ProductAdapter()
        binding.productRecyclerview.layoutManager = GridLayoutManager(this, 2)
        adapter.setOnClickListener(object : ProductAdapter.MyOnClickListener {
            override fun onClick(position: Int, product: Product) {
                val intent = Intent(applicationContext, ProductDetailsActivity::class.java)
                intent.putExtra("product", product)
                startActivity(intent)
            }
        })
    }

    private fun displayProducts() {
        productViewModel.getProducts()
        binding.progressBar.visibility = View.VISIBLE
        productViewModel.products.observe(this) {
            when (it) {
                is NetworkResult.Success -> {
                    //adapter = ProductAdapter(it.data!!)
                    adapter.updateData(it.data!!)
                    binding.productRecyclerview.adapter = adapter
                    binding.progressBar.visibility = View.GONE
                }

                is NetworkResult.Error -> {
                    Toast.makeText(applicationContext, "Data not available", Toast.LENGTH_LONG)
                        .show()
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }
}