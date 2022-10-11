package com.example.roomone.domain.UseCase

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.roomone.data.data.CategoryDao
import com.example.roomone.data.models.CategoryModel
import com.example.roomone.data.models.ProductModel
import com.example.roomone.domain.repository.CategoriesCall

class CategoriesUseCase (private val categoriesCall: CategoriesCall) {


    fun loadCategories(): LiveData<List<CategoryModel>> {

        return categoriesCall.loadCategories()

    }

    suspend fun insertCategory(categories: CategoryModel){
        categoriesCall.insertCategory(categories)
    }

    suspend fun updateCategory(categories: CategoryModel){
        categoriesCall.updateCategory(categories)
    }

    suspend fun deleteCategory(categories: CategoryModel) {
        categoriesCall.deleteCategory(categories)
    }

    suspend fun deleteAllCategories(){
        categoriesCall.deleteAllCategories()
    }

}