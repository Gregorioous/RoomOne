package com.example.roomone.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomone.data.models.CategoryModel
import com.example.roomone.domain.UseCase.CategoriesUseCase
import kotlinx.coroutines.launch

class CategoryViewModel (private val categoriesUseCase: CategoriesUseCase) : ViewModel() {

    val categories = categoriesUseCase.loadCategories()

    fun startInsert(nameCategory:String) {
        insert(CategoryModel(0, nameCategory))
    }

    fun startUpdateCategory(idCategory:Int, nameCategory:String) {
        updateCategory(CategoryModel(idCategory, nameCategory))
    }

    fun insert(categoryModel: CategoryModel) = viewModelScope.launch{

        categoriesUseCase.insertCategory(categoryModel)
    }

    fun updateCategory(categoryModel: CategoryModel) = viewModelScope.launch{

        categoriesUseCase.updateCategory(categoryModel)
    }

    fun delete(categoryModel: CategoryModel) = viewModelScope.launch{

        categoriesUseCase.deleteCategory(categoryModel)
    }

    fun deleteAll() = viewModelScope.launch{

        categoriesUseCase.deleteAllCategories()
    }


}