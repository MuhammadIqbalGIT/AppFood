package com.example.appfood.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.appfood.databinding.FragmentCategoryBinding
import com.example.appfood.ui.base.BaseFragment
import com.example.core.data.source.Resource
import kotlinx.coroutines.launch


class CategoryFragment : BaseFragment<FragmentCategoryBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCategoryBinding
        get() = FragmentCategoryBinding::inflate


    private val viewModel: CategoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initUI()
            initObserve()

        }
    }

    override fun FragmentCategoryBinding.initUI() {


    }

    override fun FragmentCategoryBinding.initObserve() {
        lifecycleScope.launch {
            viewModel.category.collect{
                when(it){
                    is Resource.Loading -> "" //not implement for handle loading
                    is Resource.Success -> Toast.makeText(requireContext(), "berhasil", Toast.LENGTH_SHORT).show()
                    else -> ""
                }
            }
        }

        viewModel.getAllCategory()
    }
}