package com.axppress.maccabitest.di

import com.axppress.maccabitest.domain.local.ProductsDao
import com.axppress.maccabitest.domain.remote.ProductsService
import com.axppress.maccabitest.domain.repository.ProductsRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val remoteModel
    get() = module {
        single { ProductsRepository(productsService, get<ProductsDao>()) }
    }

private val httpClient
    get() = OkHttpClient.Builder().callTimeout(30L, TimeUnit.SECONDS)
        .addInterceptor(
            Interceptor { chain ->
                val newRequest: Request = chain.request().newBuilder()
                    .build()
                chain.proceed(newRequest)
            },
        ).build()


private val retrofit = Retrofit.Builder()
    .baseUrl("https://dummyjson.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .client(httpClient)
    .build()

private val productsService = retrofit.create(ProductsService::class.java)
