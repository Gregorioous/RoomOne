package com.example.roomone.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomone.data.models.ProductModel
import com.example.roomone.domain.UseCase.ProductsUseCase
import kotlinx.coroutines.launch

class ProductViewModel (private val productsUseCase: ProductsUseCase) : ViewModel() {

    val products = productsUseCase.loadProducts()


    fun getFilter (nameCategory:String, priceProduct:String):
            LiveData<List<ProductModel>> {
        return productsUseCase.getFilter(nameCategory, priceProduct)
    }

    fun startInsert(nameProduct:String, categoryProduct:String, priceProduct:String) {
        insertProduct(ProductModel(0,nameProduct, categoryProduct, priceProduct))
    }

    fun startUpdateProduct(idProduct:Int, nameProduct:String, nameCategory:String, priceProduct:String) {
        updateProduct(ProductModel(idProduct, nameProduct, nameCategory, priceProduct))
    }

    fun insertProduct(productModel: ProductModel) = viewModelScope.launch{

        productsUseCase.insertProduct(productModel)
    }

    fun updateProduct(productModel: ProductModel) = viewModelScope.launch{

        productsUseCase.updateProduct(productModel)
    }

    fun deleteProduct(productModel: ProductModel) = viewModelScope.launch{

        productsUseCase.deleteProduct(productModel)
    }

    fun deleteAllProducts() = viewModelScope.launch{

        productsUseCase.deleteAllProducts()
    }


}