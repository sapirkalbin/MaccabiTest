package com.axppress.maccabitest.ui.fragment.categorydetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.axppress.maccabitest.databinding.FragmentListBinding
import com.axppress.maccabitest.ui.MainViewModel
import com.axppress.maccabitest.ui.fragment.categorydetails.common.ProductsAdapter

class CategoryListOfProductsFragment : Fragment() {
    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var binding: FragmentListBinding
    private lateinit var productsAdapter: ProductsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeAdapter()
        getProductsForCategory(viewModel.getCurrentCategory()?.name ?: "")
    }

    private fun getProductsForCategory(categoryName: String) {
        val products =
            viewModel.getProducts().value?.filter { it.category == categoryName }

        if (products.isNullOrEmpty()) {
            binding.emptyView.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
            binding.loader.visibility = View.GONE
        } else {
            productsAdapter.updateItems(products)
            binding.emptyView.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
            binding.loader.visibility = View.GONE
        }
    }

    private fun initializeAdapter() {
        productsAdapter = ProductsAdapter()
        binding.recyclerView.adapter = productsAdapter
    }
}
