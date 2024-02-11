package com.axppress.maccabitest.domain.remote

import com.axppress.maccabitest.domain.model.remote.ProductDto
import retrofit2.http.GET

interface ProductsService {
    @GET("products?limit=100")
    suspend fun getProducts(): ProductsResponse

    class ProductsResponse {
        var products: List<ProductDto> = listOf()
    }
}
