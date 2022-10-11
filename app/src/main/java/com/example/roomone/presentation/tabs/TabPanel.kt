package com.example.roomone.presentation.tabs

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.roomone.R
import com.example.roomone.databinding.TabPanelBinding
import com.example.roomone.presentation.viewModels.CategoryViewModel
import com.example.roomone.presentation.viewModels.ProductViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class TabPanel : Fragment(), View.OnClickListener, View.OnKeyListener {

    private var binding: TabPanelBinding? = null
    private val CategoryViewModel: CategoryViewModel by viewModel()
    private val ProductsViewModel: ProductViewModel by viewModel()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TabPanelBinding.inflate(inflater, container, false)



        binding?.enterNameProduct?.setOnKeyListener(this)
        binding?.enterCategoryProduct?.setOnKeyListener(this)
        binding?.enterPriceProduct?.setOnKeyListener(this)

        binding?.buttonAddProduct?.setOnClickListener(this)

        binding?.buttonAddCategoryClothes?.setOnClickListener(this)
        binding?.buttonAddCategoryShoes?.setOnClickListener(this)
        binding?.buttonAddCategoryAccessories?.setOnClickListener(this)

        return binding?.root
    }


    override fun onKey(view: View, i: Int, keyEvent: KeyEvent): Boolean {
        when (view.id) {

            R.id.enterNameProduct -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    binding?.resEnterNameProduct?.text = binding?.enterNameProduct?.text
                    binding?.enterNameProduct?.setText("")
                    return true
                }

            }

            R.id.enterCategoryProduct -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    binding?.resEnterCategoryProduct?.text = binding?.enterCategoryProduct?.text
                    binding?.enterCategoryProduct?.setText("")
                    return true
                }

            }

            R.id.enterPriceProduct -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    binding?.resEnterPriceProduct?.text = binding?.enterPriceProduct?.text
                    binding?.enterPriceProduct?.setText("")
                    return true
                }

            }
        }

        return false
    }

    override fun onClick(view: View) {

        when(view.id) {

            R.id.buttonAddCategoryClothes -> {

                CategoryViewModel?.startInsert(binding?.buttonAddCategoryClothes?.text?.toString()!!)

            }

            R.id.buttonAddCategoryShoes -> {

                CategoryViewModel?.startInsert(binding?.buttonAddCategoryShoes?.text?.toString()!!)

            }

            R.id.buttonAddCategoryAccessories -> {

                CategoryViewModel?.startInsert(binding?.buttonAddCategoryAccessories?.text?.toString()!!)

            }

            R.id.buttonAddProduct -> {

                ProductsViewModel?.startInsert(binding?.resEnterNameProduct?.text?.toString()!!,
                    binding?.resEnterCategoryProduct?.text?.toString()!!,
                    binding?.resEnterPriceProduct?.text?.toString()!!)

                clearResEnterProduct()

            }
        }

    }

    private fun clearResEnterProduct() {
        binding?.resEnterNameProduct?.setText("")
        binding?.resEnterCategoryProduct?.setText("")
        binding?.resEnterPriceProduct?.setText("")

    }


}