package com.example.roomone.presentation.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomone.databinding.TabCategoriesBinding
import com.example.roomone.data.models.CategoryModel
import com.example.roomone.presentation.viewModels.CategoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel



class TabCategories : Fragment() {

    private var binding: TabCategoriesBinding? = null
    private val CategoryViewModel: CategoryViewModel by viewModel()
    private var categoryAdapter: CategoryAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TabCategoriesBinding.inflate(inflater, container, false)



        initRecyclerCategories()
        displayCategories()

        binding?.deleteAllCategories?.setOnClickListener(View.OnClickListener {
            CategoryViewModel?.deleteAll()
        })

        return  binding?.root
    }

    private fun initRecyclerCategories(){
        binding?.recyclerCategories?.layoutManager = LinearLayoutManager(context)
        categoryAdapter = CategoryAdapter({categoryModel: CategoryModel ->deleteCategory(categoryModel)},
            {categoryModel:CategoryModel->editCategory(categoryModel)})
        binding?.recyclerCategories?.adapter = categoryAdapter



    }

    private fun displayCategories(){
        CategoryViewModel.categories.observe(viewLifecycleOwner, Observer {
            categoryAdapter?.setList(it)
            categoryAdapter?.notifyDataSetChanged()
        })
    }


    private fun deleteCategory(categoryModel: CategoryModel) {
        CategoryViewModel?.delete(categoryModel)
    }

    private fun editCategory(categoryModel:CategoryModel) {
        val panelCategory = PanelEditCategory()
        val parameters = Bundle()
        parameters.putString("idCategory", categoryModel.id.toString())
        parameters.putString("nameCategory", categoryModel.name)
        panelCategory.arguments = parameters

        panelCategory.show((context as FragmentActivity).supportFragmentManager, "editCategory")
    }


}