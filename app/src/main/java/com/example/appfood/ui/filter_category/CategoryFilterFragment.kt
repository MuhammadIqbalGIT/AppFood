package com.example.appfood.ui.filter_category

import CategoryAll
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appfood.databinding.FragmentCategoryFilterBinding
import com.example.appfood.ui.base.BaseFragment
import com.example.appfood.ui.filter_category.sheet.BottomSheetCategoryFilterFragment
import com.example.core.data.source.Resource
import com.example.core.data.source.remote.response.Meal
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryFilterFragment : BaseFragment<FragmentCategoryFilterBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCategoryFilterBinding
        get() = FragmentCategoryFilterBinding::inflate

    private val viewModel: CategoryFilterViewModel by viewModels()
    private lateinit var adapterMealByCategory: CategoryFilterAdapter
    private lateinit var mealss: List<Meal>
    private var category: String = ""


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            getData()
            initUI()
            initEvent()
            initObserve()
        }
    }

    override fun FragmentCategoryFilterBinding.initUI() {
        adapterMealByCategory = CategoryFilterAdapter()
        rvCategory.adapter = adapterMealByCategory
        rvCategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewModel.getMealsByCategory("Seafood")
    }

    override fun FragmentCategoryFilterBinding.initEvent() {
        adapterMealByCategory.onButtonDetailClick = {
            val action =
                CategoryFilterFragmentDirections.actionCategoryFilterFragmentToDetailMealFragment(it.idMeal)
            findNavController().navigate(action)
        }


        cardFilterDefault.setOnClickListener {
            showBottomSheet()
        }
    }

    override fun FragmentCategoryFilterBinding.initObserve() {
        lifecycleScope.launch {
            viewModel.category.collect {
                when (it) {
                    is Resource.Loading -> {
                        loadingDialog?.show()
                    }

                    is Resource.Success -> {
                        val data = it.data
                        adapterMealByCategory.submitList(data)
                        mealss = it.data!!
                    }

                    is Resource.Error -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> ""
                }
            }
        }
    }

    private fun showBottomSheet() {
        val sheet = BottomSheetCategoryFilterFragment()
        sheet.show(parentFragmentManager, "BottomSheetCategoryFilterFragment")
    }

    private fun getData() {
        setFragmentResultListener("request_key") { requestKey, result ->
            val selectedItem = result.getSerializable("selected_item") as? CategoryAll
            if (selectedItem != null) {
                category = selectedItem.strCategory
                viewModel.getMealsByCategory(category)
            }
        }
    }
}