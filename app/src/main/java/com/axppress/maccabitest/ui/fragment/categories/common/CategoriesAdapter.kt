package com.axppress.maccabitest.ui.fragment.categories.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.axppress.maccabitest.databinding.CategoryItemBinding
import com.axppress.maccabitest.domain.model.CategoryEntity
import com.axppress.maccabitest.utils.OnItemClickListener
import com.axppress.maccabitest.utils.Utils.firstCap
import com.bumptech.glide.Glide


class CategoriesAdapter :
    androidx.recyclerview.widget.RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>() {
    private var items: List<CategoryEntity> = emptyList()
    var onClickListener: OnItemClickListener? = null

    override fun getItemCount(): Int {
        return items.count()
    }

    fun updateItems(newItems: List<CategoryEntity>) {
        items = newItems
        refresh()
    }

    fun refresh() {
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    inner class MyViewHolder(private val binding: CategoryItemBinding) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

        fun bindItems(category: CategoryEntity) {
            binding.name.text = category.name.firstCap()
            binding.distinctProducts.text = "products: ${category.distinctNum}"
            binding.sumOfProducts.text = "products in stock: ${category.sum}"

            Glide.with(binding.root.context).load(category.thumbnail).into(binding.thumbnail);

            binding.root.setOnClickListener {
                onClickListener?.onItemClicked(category)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

}