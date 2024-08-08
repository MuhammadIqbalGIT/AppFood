package com.example.appfood.ui.detail_meal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appfood.R
import com.example.appfood.databinding.FragmentCategoryFilterBinding
import com.example.appfood.databinding.FragmentDetailMealBinding
import com.example.appfood.ui.base.BaseFragment
import com.example.appfood.ui.filter_category.CategoryFilterAdapter
import com.example.appfood.ui.filter_category.CategoryFilterFragmentDirections
import com.example.appfood.ui.filter_category.CategoryFilterViewModel
import com.example.core.data.source.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch



@AndroidEntryPoint
class DetailMealFragment : BaseFragment<FragmentDetailMealBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailMealBinding
        get() = FragmentDetailMealBinding::inflate

    private val viewModel: DetailMealViewModel by viewModels()
    private lateinit var adapterDeailMeal: DetailMealAdapter
    private val args: DetailMealFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initUI()
            initObserve()
        }
    }



    override fun FragmentDetailMealBinding.initUI() {
        adapterDeailMeal = DetailMealAdapter()
        rvMeals.adapter = adapterDeailMeal
        rvMeals.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewModel.getMealsDetailsById(args.idMeal)

    }



    override fun FragmentDetailMealBinding.initObserve() {
        lifecycleScope.launch {
            viewModel.mealsDetailsById.collect{
                when(it){
                    is Resource.Loading -> {loadingDialog?.show()}
                    is Resource.Success -> {
                        adapterDeailMeal.submitList(it.data)
                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> ""
                }
            }
        }
    }
}