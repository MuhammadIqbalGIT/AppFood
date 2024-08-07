package com.example.appfood.ui.filter_category

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appfood.R
import com.example.appfood.databinding.FragmentCategoryBinding
import com.example.appfood.databinding.FragmentCategoryFilterBinding
import com.example.appfood.ui.all_meal_category.CategoryAdapter
import com.example.appfood.ui.all_meal_category.CategoryViewModel
import com.example.appfood.ui.base.BaseFragment
import com.example.appfood.utils.widget.RecyclerViewGridSpace
import com.example.core.data.source.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryFilterFragment : BaseFragment<FragmentCategoryFilterBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCategoryFilterBinding
        get() = FragmentCategoryFilterBinding::inflate

    private val viewModel: CategoryFilterViewModel by viewModels()
    private lateinit var adapterMealByCategory: CategoryFilterAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initUI()
            initEvent()
            initObserve()
        }
    }



    private val Int.px: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()
    override fun FragmentCategoryFilterBinding.initUI() {
        adapterMealByCategory = CategoryFilterAdapter()
        rvCategory.adapter = adapterMealByCategory
        rvCategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewModel.getMealsByCategory("Seafood")

    }

    override fun FragmentCategoryFilterBinding.initEvent() {
        adapterMealByCategory.onButtonDetailClick = {
            val action = CategoryFilterFragmentDirections.actionCategoryFilterFragmentToDetailMealFragment(it.idMeal)
            Log.d("dsadasdasdasdsadadas",it.idMeal)
            findNavController().navigate(action)
        }
    }

    override fun FragmentCategoryFilterBinding.initObserve() {
        lifecycleScope.launch {
            viewModel.category.collect{
                when(it){
                    is Resource.Loading -> "" //not implement for handle loading
                    is Resource.Success -> {
                        adapterMealByCategory.submitList(it.data)
                        Toast.makeText(requireContext(), "berhasil", Toast.LENGTH_SHORT).show()
                    }
                    else -> ""
                }
            }
        }
    }
}