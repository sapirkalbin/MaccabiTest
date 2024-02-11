package com.axppress.maccabitest.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axppress.maccabitest.domain.model.CategoryEntity
import com.axppress.maccabitest.domain.model.local.Product
import com.axppress.maccabitest.domain.usecases.GetProductsUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val getProductsUseCase: GetProductsUseCase,
) : ViewModel() {
    private var products: MutableLiveData<List<Product>?> = MutableLiveData()
    private var currentCategory: CategoryEntity? = null

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()

        // Network error (or other error) occurred
        CoroutineScope(Dispatchers.Main).launch {
            products.value = null
        }
    }

    init {
        getProductsFromServerOrDb()
    }

    // Get products from db. if not exist, get from server
    private fun getProductsFromServerOrDb() {
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            val productsList = getProductsUseCase.invoke()
            CoroutineScope(Dispatchers.Main).launch {
                products.value = productsList
            }
        }
    }

    fun getProducts() = products
    fun setCategory(category: CategoryEntity) {
        currentCategory = category
    }

    fun getCurrentCategory() = currentCategory
}