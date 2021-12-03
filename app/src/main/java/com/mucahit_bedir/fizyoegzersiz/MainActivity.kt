package com.mucahit_bedir.fizyoegzersiz

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mucahit_bedir.fizyoegzersiz.databinding.ActivityMainBinding
import com.mucahit_bedir.fizyoegzersiz.ui.SharedViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObserver()
    }

    fun initObserver() {
        viewModel.bottomNavVisibilityLiveData.observe(this) {
            binding.bottomNavigationView.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

}