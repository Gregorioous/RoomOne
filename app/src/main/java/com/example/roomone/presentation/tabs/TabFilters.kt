package com.example.roomone.presentation.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomone.data.data.Database
import com.example.roomone.databinding.TabFiltersBinding
import com.example.roomone.data.models.ProductModel
import com.example.roomone.data.repositories.ProductRepository
import com.example.roomone.presentation.viewModels.ProductViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class TabFilters : Fragment() {

    private var binding: TabFiltersBinding? = null
    private var productRepository: ProductRepository? = null
    private val ProductsViewModel: ProductViewModel by viewModel()
    private var productAdapter: ProductAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TabFiltersBinding.inflate(inflater, container, false)


        return binding?.root
    }

    private fun initRecyclerFilterProducts(){
        binding?.recyclerFilter?.layoutManager = LinearLayoutManager(context)
        productAdapter = ProductAdapter({productModel: ProductModel ->deleteProduct(productModel)},
            {productModel: ProductModel ->editProduct(productModel)})
        binding?.recyclerFilter?.adapter = productAdapter

        displayFilterProducts()
    }

    private fun displayFilterProducts(){
        ProductsViewModel?.getFilter("одежда", "5000")?.observe(viewLifecycleOwner, Observer {
            productAdapter?.setList(it)
            productAdapter?.notifyDataSetChanged()
        })
    }

    private fun deleteProduct(productModel:ProductModel) {
        ProductsViewModel?.deleteProduct(productModel)
    }

    private fun editProduct(productModel:ProductModel) {
        val panelEditProduct = PanelEditProduct()
        val parameters = Bundle()
        parameters.putString("idProduct", productModel.id.toString())
        parameters.putString("nameProduct", productModel.name)
        parameters.putString("categoryProduct", productModel.category)
        parameters.putString("priceProduct", productModel.price)
        panelEditProduct.arguments = parameters

        panelEditProduct.show((context as FragmentActivity).supportFragmentManager, "editProduct")
    }


}