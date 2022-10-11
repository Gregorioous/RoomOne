package com.example.roomone.presentation.tabs

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.roomone.R
import com.example.roomone.data.data.Database
import com.example.roomone.databinding.PanelEditProductBinding
import com.example.roomone.data.repositories.ProductRepository
import com.example.roomone.presentation.viewModels.ProductViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.android.compat.ScopeCompat.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PanelEditProduct : BottomSheetDialogFragment(), View.OnKeyListener, View.OnClickListener {

    private var binding: PanelEditProductBinding? = null
    private val ProductsViewModel: ProductViewModel by viewModel()
    private var idProduct:Int? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = PanelEditProductBinding.inflate(inflater, container, false)

        idProduct = arguments?.getString("idProduct")?.toInt()
        binding?.editNameProduct?.setText(arguments?.getString("nameProduct").toString())
        binding?.editCategoryProduct?.setText(arguments?.getString("categoryProduct").toString())
        binding?.editPriceProduct?.setText(arguments?.getString("priceProduct").toString())


        binding?.editNameProduct?.setOnKeyListener(this)
        binding?.editCategoryProduct?.setOnKeyListener(this)
        binding?.editPriceProduct?.setOnKeyListener(this)

        binding?.buttonEditProduct?.setOnClickListener(this)


        return binding?.root
    }

    override fun onKey(view: View, i: Int, keyEvent: KeyEvent): Boolean {
        when (view.id) {


            R.id.editNameProduct -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {

                    binding?.resEditNameProduct?.text = binding?.editNameProduct?.text
                    binding?.editNameProduct?.setText("")

                    return true
                }

            }

            R.id.editCategoryProduct -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {

                    binding?.resEditCategoryProduct?.text = binding?.editCategoryProduct?.text
                    binding?.editCategoryProduct?.setText("")

                    return true
                }

            }

            R.id.editPriceProduct -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {

                    binding?.resEditPriceProduct?.text = binding?.editPriceProduct?.text
                    binding?.editPriceProduct?.setText("")

                    return true
                }

            }
        }

        return false
    }

    override fun onClick(view: View) {
        ProductsViewModel?.startUpdateProduct(idProduct.toString().toInt(), binding?.resEditNameProduct?.text?.toString()!!,
            binding?.resEditCategoryProduct?.text?.toString()!!, binding?.resEditPriceProduct?.text?.toString()!!)

        dismiss()

        (context as FragmentActivity).supportFragmentManager.beginTransaction().replace(R.id.content, TabProducts()).commit()
    }

}