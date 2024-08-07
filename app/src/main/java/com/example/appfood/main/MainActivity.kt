package com.example.appfood.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.appfood.R
import com.example.appfood.databinding.ActivityMainBinding
import com.example.appfood.ui.category.CategoryViewModel
import com.example.core.data.source.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    private val viewModel: CategoryViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val host: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainSupplierNewRefactorNavHostFragment) as NavHostFragment
        navController = host.navController

        val navGraph =
            navController.navInflater.inflate(R.navigation.nav_main)
        navController.graph = navGraph

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.categoryFragment ||
                destination.id == R.id.fragA ||
                destination.id == R.id.fragB
            ) {
                binding.navView.visibility = View.VISIBLE
            } else {
                binding.navView.visibility = View.GONE
            }
        }

        binding.navView.setupWithNavController(navController)

        lifecycleScope.launch {
            viewModel.category.collect{
                when(it){
                    is Resource.Loading -> ""
                    is Resource.Success -> {
                        Toast.makeText(this@MainActivity, "berhasil", Toast.LENGTH_SHORT).show()
                        Log.d("TAG", "onCrsdfsasadfsdeate: ${it.data}")
                        Log.d("dsadsasadada", "onCrsdfsasadfsdeate: ${it.data}")
                    }
                    else -> ""
                }
            }
        }

        viewModel.getAllCategory()
    }

}