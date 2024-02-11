package com.axppress.maccabitest.di

import com.axppress.maccabitest.domain.repository.ProductsRepository
import com.axppress.maccabitest.domain.usecases.GetProductsUseCase
import com.axppress.maccabitest.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModels = module {
    viewModel {
        MainViewModel(
            getProductsUseCase = get<GetProductsUseCase>()
        )
    }
}

val usecases = module {
    factory { GetProductsUseCase(repository = get<ProductsRepository>()) }
}
