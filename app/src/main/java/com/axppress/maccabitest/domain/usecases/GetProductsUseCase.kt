package com.axppress.maccabitest.domain.usecases

import com.axppress.maccabitest.domain.model.local.Product
import com.axppress.maccabitest.domain.repository.ProductsRepository

class GetProductsUseCase(private val repository: ProductsRepository) {
    // Get products from db. if not exist, get from server
    suspend operator fun invoke(): List<Product> {
        val products = repository.getProductsFromDb()
        return products.ifEmpty {
            // Get products from server
            val list = repository.getProducts().map { it.toModel() }
            // saving products to db
            repository.saveProducts(list)
            list
        }
    }
}
