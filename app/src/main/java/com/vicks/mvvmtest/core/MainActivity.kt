package com.vicks.mvvmtest.core

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.vicks.mvvmtest.R
import com.vicks.mvvmtest.databinding.ActivityMainBinding
import com.vicks.mvvmtest.products.presentation.ProductActivity

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.btnProducts.setOnClickListener{
            val intent = Intent(this, ProductActivity::class.java)
            startActivity(intent)
        }

        /*recyclerView = findViewById(R.id.productList)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val repository = (application as StoreApplication).productRepository
        productViewModel =
            ViewModelProvider(this, ProductViewModelFactory(repository)).get(ProductViewModel::class.java)
        productViewModel.getProducts()

        productViewModel.products.observe(this, Observer {
            when (it) {
                is NetworkResult.Success -> {
                    adapter = ProductAdapter(it.data!!)
                    recyclerView.adapter = adapter
                }

                is NetworkResult.Error -> {
                    Log.d("APP", "ERROR")

                }
            }
        })*/
    }
}