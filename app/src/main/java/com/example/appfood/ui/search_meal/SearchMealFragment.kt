package com.example.appfood.ui.search_meal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appfood.databinding.FragmentSearchMealBinding
import com.example.appfood.ui.base.BaseFragment
import com.example.appfood.ui.detail_meal.DetailMealAdapter
import com.example.core.data.source.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SearchMealFragment : BaseFragment<FragmentSearchMealBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchMealBinding
        get() = FragmentSearchMealBinding::inflate

    private val viewModel: SearchMealViewModel by viewModels()
    private lateinit var adapterDeailMeal: DetailMealAdapter
    private var searchDebounceReady = true
    private val searchKey = MutableStateFlow("")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initUI()
            initEvent()
            initObserve()
        }
    }


    override fun FragmentSearchMealBinding.initUI() {
        adapterDeailMeal = DetailMealAdapter()
        rvMeals.adapter = adapterDeailMeal
        rvMeals.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun FragmentSearchMealBinding.initEvent() {
        etSearch.setOnClickListener {
            searchDebounceReady = true

            etSearch.requestFocus()
        }

        etSearch.doAfterTextChanged {
            searchKey.value = (etSearch.text?.trim() ?: "").toString()
        }
    }


    @OptIn(FlowPreview::class)
    override fun FragmentSearchMealBinding.initObserve() {
        lifecycleScope.launch {
            viewModel.mealByName.collect {
                when (it) {
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
        lifecycleScope.launch {
            searchKey.debounce(800).collect {
                if (searchDebounceReady) {
                    viewModel.getMealByName(it)
                }
            }
        }
    }
}