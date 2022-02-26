package com.mucahit_bedir.fizyoegzersiz

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.mucahit_bedir.fizyoegzersiz.databinding.ActivityMainBinding
import com.mucahit_bedir.fizyoegzersiz.ui.SharedViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: SharedViewModel by viewModels()

    private val navController: NavController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObserver()
        initListeners()
    }

    fun initObserver() {
        viewModel.bottomNavVisibilityLiveData.observe(this) {
            binding.bottomNavigationView.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun initListeners() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.profile -> {
                    navController.navigate(R.id.action_global_profileFragment)
                }
                R.id.exercise -> {
                    navController.navigate(R.id.action_global_egzersizFragment)
                }
                R.id.calendar -> {
                    navController.navigate(R.id.action_global_calendarFragment)
                }
                R.id.home -> {
                    navController.navigate(R.id.action_global_homeFragment)
                }
                R.id.notification -> {
                    navController.navigate(R.id.action_global_notificationFragment)
                }
            }
            return@setOnItemSelectedListener true
        }
    }

}