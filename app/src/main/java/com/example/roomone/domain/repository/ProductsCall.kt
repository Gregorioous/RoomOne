package com.example.roomone.domain.repository

import androidx.lifecycle.LiveData
import com.example.roomone.data.models.ProductModel

interface ProductsCall {

    fun loadCategories(): LiveData<List<ProductModel>>

   fun getFilter (nameCategory:String, priceProduct:String):
    LiveData<List<ProductModel>>

    suspend fun insertProduct(productModel: ProductModel)

    suspend fun updateProduct(productModel: ProductModel)

    suspend fun deleteProduct(productModel: ProductModel)

    suspend fun deleteAllProducts()
}