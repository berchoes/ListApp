package com.example.listapp.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.listapp.base.BaseActivity
import com.example.listapp.common.SEND_POST_TO_DETAIL_PAGE
import com.example.listapp.databinding.ActivityHomeBinding
import com.example.listapp.domain.model.PostModel
import com.example.listapp.presentation.details.PostDetailsActivity
import com.example.listapp.util.fetch
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    @Inject lateinit var postListAdapter: PostListAdapter

    private val viewModel: HomeViewModel by viewModels()

    override fun getViewBinding() = ActivityHomeBinding.inflate(layoutInflater)

    override fun onCreateFinished(savedInstanceState: Bundle?) {
        observeViewModel()
        binding.rvPosts.adapter = postListAdapter

        postListAdapter.onItemClicked = onPostClicked
    }

    private val onPostClicked : (PostModel) -> Unit = {
        val intent = Intent(this,PostDetailsActivity::class.java)
        intent.putExtra(SEND_POST_TO_DETAIL_PAGE, Gson().toJson(it))
        startActivity(intent)
    }

    private fun observeViewModel() {
        fetch(viewModel.posts){
            if(it.isNotEmpty())  postListAdapter.submitList(it)
        }

        fetch(viewModel.errorMessage){}

        fetch(viewModel.isLoading){}
    }
}