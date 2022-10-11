package com.example.roomone.data.repositories

import androidx.lifecycle.LiveData
import com.example.roomone.data.data.ProductDao
import com.example.roomone.data.models.CategoryModel
import com.example.roomone.data.models.ProductModel
import com.example.roomone.domain.repository.ProductsCall

class ProductRepository (private val productDAO: ProductDao):ProductsCall {

    override fun loadCategories(): LiveData<List<ProductModel>> {
        return productDAO.getAllProducts()
    }

    override fun getFilter (nameCategory:String, priceProduct:String):
            LiveData<List<ProductModel>> {
        return productDAO.getFilter(nameCategory, priceProduct)
    }


    override suspend fun insertProduct(productModel: ProductModel){
        productDAO.insertProduct(productModel)
    }

    override suspend fun updateProduct(productModel: ProductModel){
        productDAO.updateProduct(productModel)
    }

    override suspend fun deleteProduct(productModel: ProductModel) {
        productDAO.deleteProduct(productModel)
    }

    override suspend fun deleteAllProducts(){
        productDAO.deleteAllProducts()
    }
}