package com.vicks.mvvmtest.products.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.vicks.mvvmtest.R
import com.vicks.mvvmtest.databinding.ActivityProductDetailsBinding
import com.vicks.mvvmtest.products.data.models.Product

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_details)

        val intent = intent
        var product:Product? = null

        if(intent.hasExtra("product")){
            // get the Serializable data model class with the details in it
            product =
                intent.getSerializableExtra("product") as Product
        }

        if (product!=null){
            binding.productName.text = product.title
            Glide.with(binding.productImage.context).load(product.image).into(binding.productImage)
        }
    }
}