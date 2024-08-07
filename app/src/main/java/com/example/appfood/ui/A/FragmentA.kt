package com.example.appfood.ui.A

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appfood.R
import com.example.appfood.databinding.FragmentABinding
import com.example.appfood.ui.base.BaseFragment


class FragmentA : BaseFragment<FragmentABinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentABinding
        get() = FragmentABinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

        }
    }

}