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
import com.example.roomone.databinding.PanelEditCategoryBinding
import com.example.roomone.data.repositories.CategoryRepository
import com.example.roomone.presentation.viewModels.CategoryViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.android.compat.ScopeCompat.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class PanelEditCategory : BottomSheetDialogFragment(),View.OnKeyListener {

    private var binding: PanelEditCategoryBinding? = null
    private val CategoryViewModel: CategoryViewModel by viewModel()
    private var idCategory:Int? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = PanelEditCategoryBinding.inflate(inflater, container, false)

        idCategory = arguments?.getString("idCategory")?.toInt()
        binding?.editCategory?.setText(arguments?.getString("nameCategory").toString())


        binding?.editCategory?.setOnKeyListener(this)

        return binding?.root
    }

    override fun onKey(view: View, i: Int, keyEvent: KeyEvent): Boolean {
        when (view.id) {


            R.id.editCategory -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {

                    CategoryViewModel?.startUpdateCategory(idCategory.toString().toInt(), binding?.editCategory?.text?.toString()!!)

                    binding?.editCategory?.setText("")

                    dismiss()

                    (context as FragmentActivity).supportFragmentManager.beginTransaction().replace(R.id.content, TabCategories()).commit()

                    return true
                }

            }
        }

        return false
    }

}