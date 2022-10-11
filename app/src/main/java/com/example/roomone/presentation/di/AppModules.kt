package com.example.roomone.presentation.di

import androidx.room.Room
import com.example.roomone.data.repositories.CategoryRepository
import com.example.roomone.data.repositories.ProductRepository
import com.example.roomone.domain.UseCase.CategoriesUseCase
import com.example.roomone.domain.UseCase.ProductsUseCase
import com.example.roomone.domain.repository.CategoriesCall
import com.example.roomone.domain.repository.ProductsCall
import com.example.roomone.presentation.viewModels.CategoryViewModel
import com.example.roomone.presentation.viewModels.ProductViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moduleCategories = module{

    single {
        Room.databaseBuilder(androidContext(), com.example.roomone.data.data.Database::class.java,
            "categoriesLocalDB").build()
    }

    single { get<com.example.roomone.data.data.Database>().categoryDAO }

    single<CategoriesCall> { CategoryRepository(get()) }

    single { CategoriesUseCase(get()) }

    viewModel { CategoryViewModel(get()) }

}

val moduleProducts = module{

    single {
        Room.databaseBuilder(androidContext(), com.example.roomone.data.data.Database::class.java,
            "productsLocalDB").build()
    }

    single { get<com.example.roomone.data.data.Database>().productDAO }

    single<ProductsCall> { ProductRepository(get()) }

    single { ProductsUseCase(get()) }

    viewModel { ProductViewModel(get()) }

}


val moduleDBProducts = module{

    single {
        Room.databaseBuilder(androidContext(), com.example.roomone.data.data.Database::class.java,
            "dbProducts").build()
    }

    single { get<com.example.roomone.data.data.Database>().productDAO }

}


val moduleDBCategories = module{

    single {
        Room.databaseBuilder(androidContext(), com.example.roomone.data.data.Database::class.java,
            "dbCategories").build()
    }

    single { get<com.example.roomone.data.data.Database>().categoryDAO }

}



val moduleCateg = module{

    moduleDBCategories

    single<CategoriesCall> { CategoryRepository(get()) }

    single { CategoriesUseCase(get()) }

    viewModel { CategoryViewModel(get()) }

}

val moduleProd = module{

    moduleDBProducts

    single<ProductsCall> { ProductRepository(get()) }

    single { ProductsUseCase(get()) }

    viewModel { ProductViewModel(get()) }

}
