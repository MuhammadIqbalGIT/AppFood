package com.example.appfood.ui.all_meal_category

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.appfood.databinding.FragmentCategoryBinding
import com.example.appfood.ui.base.BaseFragment
import com.example.appfood.utils.widget.RecyclerViewGridSpace
import com.example.core.data.source.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCategoryBinding
        get() = FragmentCategoryBinding::inflate


    private val viewModel: CategoryViewModel by viewModels()
    private lateinit var adapterCategory: CategoryAdapter



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initUI()
            initObserve()

        }
    }
    private val Int.px: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()
    override fun FragmentCategoryBinding.initUI() {
        adapterCategory = CategoryAdapter()

        val decorGrid = RecyclerViewGridSpace(2, 16.px, false)

        viewLifecycleOwner.lifecycleScope.launch {

            rvCategory.apply {
                adapter = adapterCategory
                addItemDecoration(decorGrid)
            }
        }

    }

    override fun FragmentCategoryBinding.initObserve() {
        lifecycleScope.launch {
            viewModel.category.collect{
                when(it){
                    is Resource.Loading -> "" //not implement for handle loading
                    is Resource.Success -> {
                        adapterCategory.submitList(it.data)
                        Toast.makeText(requireContext(), "berhasil", Toast.LENGTH_SHORT).show()
                    }
                    else -> ""
                }
            }
        }

        viewModel.getAllCategory()
    }
}