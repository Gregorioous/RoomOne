package com.example.roomone.presentation.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomone.databinding.TabProductsBinding
import com.example.roomone.data.models.ProductModel
import com.example.roomone.presentation.viewModels.ProductViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class TabProducts : Fragment() {

    private var binding: TabProductsBinding? = null
    private val ProductsViewModel: ProductViewModel by viewModel()
    private var productAdapter: ProductAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TabProductsBinding.inflate(inflater, container, false)


        initRecyclerProducts()

        binding?.deleteAllProducts?.setOnClickListener(View.OnClickListener {
            ProductsViewModel?.deleteAllProducts()
        })

        return binding?.root
    }

    private fun initRecyclerProducts(){
        binding?.recyclerProducts?.layoutManager = LinearLayoutManager(context)
        productAdapter = ProductAdapter({productModel: ProductModel ->deleteProduct(productModel)},
            {productModel: ProductModel ->editProduct(productModel)})
        binding?.recyclerProducts?.adapter = productAdapter

        displayProducts()
    }

    private fun displayProducts(){
        ProductsViewModel?.products?.observe(viewLifecycleOwner, Observer {
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