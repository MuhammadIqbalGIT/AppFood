package com.example.appfood.ui.detail_meal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appfood.R
import com.example.appfood.databinding.FragmentDetailMealBinding
import com.example.appfood.ui.base.BaseFragment


class DetailMealFragment : BaseFragment<FragmentDetailMealBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailMealBinding
        get() = FragmentDetailMealBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initUI()
        }
    }

    override fun FragmentDetailMealBinding.initUI() {

    }

}