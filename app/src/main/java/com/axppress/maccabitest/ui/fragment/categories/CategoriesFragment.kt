package com.axppress.maccabitest.ui.fragment.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.axppress.maccabitest.R
import com.axppress.maccabitest.databinding.FragmentListBinding
import com.axppress.maccabitest.domain.model.CategoryEntity
import com.axppress.maccabitest.domain.model.local.Product
import com.axppress.maccabitest.ui.MainActivity
import com.axppress.maccabitest.ui.MainViewModel
import com.axppress.maccabitest.ui.fragment.categories.common.CategoriesAdapter
import com.axppress.maccabitest.utils.OnItemClickListener
import com.axppress.maccabitest.utils.Utils.firstCap

class CategoriesFragment : Fragment() {
    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var binding: FragmentListBinding
    private lateinit var categoriesAdapter: CategoriesAdapter


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

        viewModel.getProducts().observe(viewLifecycleOwner) { products ->
            if (products.isNullOrEmpty()) {
                errorUI()
            } else {
                showProductsOnUI(products)
            }
        }
    }

    // Show the error UI
    private fun errorUI() {
        binding.emptyView.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
        binding.loader.visibility = View.GONE
    }

    // Show the categories on the UI
    private fun showProductsOnUI(products: List<Product>) {
        val categories = categoryEntities(products)
        categoriesAdapter.updateItems(categories)
        binding.emptyView.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
        binding.loader.visibility = View.GONE
    }

    private fun categoryEntities(products: List<Product>) =
        products.map { it.category }.distinct().map { category ->
            // distinctNum is the number of different products in the category
            val distinctNum = products.filter { it.category == category }.distinctBy { it.id }.size

            // sum is the sum of the stock of all products in the category
            val sum = products.filter { it.category == category }.map { it.stock }.sum()

            // thumbnail is the thumbnail of the first product in the category
            val thumbnail = products.find { it.category == category }?.thumbnail ?: ""

            CategoryEntity(category, thumbnail, distinctNum, sum)
        }

    private fun initializeAdapter() {
        categoriesAdapter = CategoriesAdapter()
        categoriesAdapter.onClickListener = object : OnItemClickListener {
            override fun <T> onItemClicked(item: T) {
                viewModel.setCategory(item as CategoryEntity)

                // Set the header of the main activity to the name of the category
                (requireActivity() as? MainActivity)?.setHeader(item.name.firstCap())

                goToCategoryListOfProducts()
            }
        }
        binding.recyclerView.adapter = categoriesAdapter
    }

    // Navigate to the category list of products
    private fun goToCategoryListOfProducts() {
        findNavController().navigate(R.id.action_categoriesFragment_to_categoryDetailsFragment)
    }
}
