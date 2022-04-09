package com.example.listapp.presentation.details

import android.os.Bundle
import androidx.activity.viewModels
import com.example.listapp.base.BaseActivity
import com.example.listapp.databinding.ActivityPostDetailsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PostDetailsActivity : BaseActivity<ActivityPostDetailsBinding>() {

    private val viewModel: PostDetailsViewModel by viewModels()

    override fun getViewBinding() = ActivityPostDetailsBinding.inflate(layoutInflater)

    override fun onCreateFinished(savedInstanceState: Bundle?) {
        setViews()
    }

    private fun setViews(){
        viewModel.currentPost?.let {
            binding.tvDetailTitle.text = it.title
            binding.tvDetailBody.text = it.body
        }
    }
}