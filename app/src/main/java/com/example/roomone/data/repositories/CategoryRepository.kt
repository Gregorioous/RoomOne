package com.example.roomone.data.repositories

import androidx.lifecycle.LiveData
import com.example.roomone.data.data.CategoryDao
import com.example.roomone.data.models.CategoryModel
import com.example.roomone.domain.repository.CategoriesCall

class CategoryRepository (private val categoryDao: CategoryDao):CategoriesCall {

    override fun loadCategories(): LiveData<List<CategoryModel>> {
            return categoryDao.getAllCategories()
    }


    override suspend fun insertCategory(categoryModel: CategoryModel){
        categoryDao.insertCategory(categoryModel)
    }

    override suspend fun updateCategory(categoryModel: CategoryModel){
        categoryDao.updateCategory(categoryModel)
    }

    override suspend fun deleteCategory(categoryModel: CategoryModel) {
        categoryDao.deleteCategory(categoryModel)
    }

    override suspend fun deleteAllCategories(){
        categoryDao.deleteAllCategories()
    }
}