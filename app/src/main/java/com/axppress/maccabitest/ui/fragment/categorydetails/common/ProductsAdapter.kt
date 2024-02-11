package com.axppress.maccabitest.ui.fragment.categorydetails.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.axppress.maccabitest.databinding.ProductItemBinding
import com.axppress.maccabitest.domain.model.local.Product
import com.axppress.maccabitest.utils.Utils.firstCap
import com.bumptech.glide.Glide


class ProductsAdapter :
    androidx.recyclerview.widget.RecyclerView.Adapter<ProductsAdapter.MyViewHolder>() {
    private var items: List<Product> = emptyList()

    override fun getItemCount(): Int {
        return items.count()
    }

    fun updateItems(newItems: List<Product>) {
        items = newItems
        refresh()
    }

    fun refresh() {
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    inner class MyViewHolder(private val binding: ProductItemBinding) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

        fun bindItems(product: Product) {
            binding.name.text = product.title.firstCap()
            binding.price.text = "Price: ${product.price}$"
            binding.stockQuantity.text = "In stock: ${product.stock}"

            Glide.with(binding.root.context).load(product.thumbnail).into(binding.thumbnail);
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

}