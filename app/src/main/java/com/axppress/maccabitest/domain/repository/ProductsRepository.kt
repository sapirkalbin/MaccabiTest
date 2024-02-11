package com.axppress.maccabitest.domain.repository

import com.axppress.maccabitest.domain.local.ProductsDao
import com.axppress.maccabitest.domain.model.local.Product
import com.axppress.maccabitest.domain.model.remote.ProductDto
import com.axppress.maccabitest.domain.remote.ProductsService

class ProductsRepository(
    private val service: ProductsService,
    private val productsDao: ProductsDao,
) {
    suspend fun getProducts() = service.getProducts().products
    fun getProductsFromDb() = productsDao.getProducts()
    fun saveProducts(listOfProducts: List<Product>) {
        productsDao.insertAll(listOfProducts)
    }
}
