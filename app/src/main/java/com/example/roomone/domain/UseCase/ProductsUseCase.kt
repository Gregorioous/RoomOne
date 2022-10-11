package com.example.roomone.domain.UseCase

import androidx.lifecycle.LiveData
import com.example.roomone.data.models.ProductModel
import com.example.roomone.domain.repository.ProductsCall

class ProductsUseCase (private val productsCall: ProductsCall) {


    fun loadProducts(): LiveData<List<ProductModel>> {

        return productsCall.loadCategories()

    }

    fun getFilter (nameCategory:String, priceProduct:String):
            LiveData<List<ProductModel>> {

        return productsCall.getFilter(nameCategory, priceProduct)

    }

    suspend fun insertProduct(products: ProductModel){
        productsCall.insertProduct(products)
    }

    suspend fun updateProduct(products: ProductModel){
        productsCall.updateProduct(products)
    }

    suspend fun deleteProduct(products: ProductModel) {
        productsCall.deleteProduct(products)
    }

    suspend fun deleteAllProducts(){
        productsCall.deleteAllProducts()
    }

}