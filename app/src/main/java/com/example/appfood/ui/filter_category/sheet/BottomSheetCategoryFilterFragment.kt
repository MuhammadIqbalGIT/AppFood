package com.example.appfood.ui.filter_category.sheet

import CategoryAll
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appfood.databinding.FragmentBottomSheetCategoryFilterBinding
import com.example.appfood.ui.all_meal_category.CategoryViewModel
import com.example.appfood.ui.base.BaseBottomSheetFragment
import com.example.appfood.ui.detail_meal.DetailMealAdapter
import com.example.core.data.source.Resource
import com.example.core.data.source.remote.response.Meal
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class BottomSheetCategoryFilterFragment :
    BaseBottomSheetFragment<FragmentBottomSheetCategoryFilterBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentBottomSheetCategoryFilterBinding
        get() = FragmentBottomSheetCategoryFilterBinding::inflate


    private lateinit var adapter: AllCategoryAdapter
    private val viewModel: AllCategoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initUI()
            initEvent()
            initObserve()
        }
    }

    override fun FragmentBottomSheetCategoryFilterBinding.initUI() {
        adapter = AllCategoryAdapter()
        rvListCategory.adapter = adapter
        rvListCategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

    }

    override fun FragmentBottomSheetCategoryFilterBinding.initEvent() {

        ivClose.setOnClickListener {
            dialog?.dismiss()
        }

        adapter.onClickListener = { selectedItem ->
            val resultBundle = Bundle().apply {
                putSerializable("selected_item", selectedItem)
            }
            setFragmentResult("request_key", resultBundle)
            dismiss()
        }

    }

    override fun FragmentBottomSheetCategoryFilterBinding.initObserve() {
        lifecycleScope.launch {
            viewModel.allCategory.collect{
                when(it){
                    is Resource.Loading ->{loadingDialog.show()}
                    is Resource.Success -> {
                        adapter.submitList(it.data)
                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> ""
                }
            }
        }
        viewModel.getListCategory()
    }
}
