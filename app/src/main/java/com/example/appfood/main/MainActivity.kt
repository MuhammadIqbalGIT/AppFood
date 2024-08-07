package com.example.appfood.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.appfood.R
import com.example.appfood.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController




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
                destination.id == R.id.categoryFilterFragment ||
                destination.id == R.id.fragB
            ) {
                binding.navView.visibility = View.VISIBLE
            } else {
                binding.navView.visibility = View.GONE
            }
        }
        binding.navView.setupWithNavController(navController)
    }
}