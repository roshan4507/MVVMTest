package com.vicks.mvvmtest.products.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vicks.mvvmtest.R
import com.vicks.mvvmtest.databinding.ProductItemLayoutBinding
import com.vicks.mvvmtest.products.data.models.Product
import kotlin.properties.Delegates

class ProductAdapter :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var onClickListener: MyOnClickListener? = null

    private var productList: List<Product> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context)
        val binding: ProductItemLayoutBinding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.product_item_layout,
                parent,
                false
            )
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
        holder.itemView.setOnClickListener {
            onClickListener?.onClick(position, product)
        }
    }

    class ProductViewHolder(private val binding: ProductItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.productName.text = product.title
            Glide.with(binding.productImage.context).load(product.image).into(binding.productImage)
        }
    }

    fun setOnClickListener(listener: MyOnClickListener?) {
        this.onClickListener = listener
    }

    fun updateData(newProductList: List<Product>) {
        productList = newProductList
    }


    interface MyOnClickListener {
        fun onClick(position: Int, product: Product)
    }
}
