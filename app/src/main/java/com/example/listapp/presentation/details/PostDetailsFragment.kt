package com.example.listapp.presentation.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.listapp.R
import com.example.listapp.databinding.FragmentPostDetailsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PostDetailsFragment : Fragment(R.layout.fragment_post_details) {

    private val viewModel: PostDetailsViewModel by viewModels()
    private lateinit var binding: FragmentPostDetailsBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPostDetailsBinding.bind(view)
        setViews()
    }


    private fun setViews() {
        viewModel.currentPost?.let {
            binding.tvDetailTitle.text = it.title
            binding.tvDetailBody.text = it.body
        }
    }

}