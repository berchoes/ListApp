package com.example.listapp.presentation.home

import android.os.Bundle
import androidx.activity.viewModels
import com.example.listapp.base.BaseActivity
import com.example.listapp.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    override fun getViewBinding() = ActivityHomeBinding.inflate(layoutInflater)

    override fun onCreateFinished(savedInstanceState: Bundle?) {
        binding.tvText.text = "HELLO CANIMIN İÇİ"
    }


    private fun observeViewModel(){

    }
}