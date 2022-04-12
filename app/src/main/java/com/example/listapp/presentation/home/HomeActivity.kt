package com.example.listapp.presentation.home

import android.os.Bundle
import androidx.activity.viewModels
import com.example.listapp.R
import com.example.listapp.base.BaseActivity
import com.example.listapp.common.DETAILS_FRAGMENT
import com.example.listapp.common.SEND_POST_TO_DETAIL_PAGE
import com.example.listapp.databinding.ActivityHomeBinding
import com.example.listapp.domain.model.PostModel
import com.example.listapp.presentation.details.PostDetailsFragment
import com.example.listapp.util.extensions.fetch
import com.example.listapp.util.extensions.gone
import com.example.listapp.util.extensions.visible
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    @Inject
    lateinit var postListAdapter: PostListAdapter

    private val viewModel: HomeViewModel by viewModels()

    override fun getViewBinding() = ActivityHomeBinding.inflate(layoutInflater)

    override fun onCreateFinished(savedInstanceState: Bundle?) {
        observeViewModel()
        binding.rvPosts.adapter = postListAdapter
        postListAdapter.onItemClicked = onPostClicked
        binding.rvPosts.itemAnimator = CustomAnimator()
    }

    private val onPostClicked: (PostModel) -> Unit = {
        goToDetailsFragment(it)
    }

    private fun observeViewModel() {
        fetch(viewModel.posts) {
            if (it.isNotEmpty()) {
                postListAdapter.submitList(it)
                binding.rvPosts.scheduleLayoutAnimation()
            }
        }

        fetch(viewModel.errorMessage) { errMsg ->
            errMsg?.let {
                binding.tvError.text = it
                binding.tvError.visible()
                binding.rvPosts.gone()
            }
        }

        fetch(viewModel.isFragmentVisible) {
            if (it) binding.frameLayout.visible() else binding.frameLayout.gone()
        }
    }

    private fun goToDetailsFragment(post: PostModel) {
        setFragmentState(isVisible = true)

        val bundle = Bundle()
        bundle.putString(SEND_POST_TO_DETAIL_PAGE, Gson().toJson(post))
        val detailsFragment = PostDetailsFragment()
        detailsFragment.arguments = bundle

        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_in, 0, 0, R.anim.fade_out)
        transaction.replace(R.id.frameLayout, detailsFragment).addToBackStack(DETAILS_FRAGMENT)
        transaction.commit()
    }

    fun setFragmentState(isVisible: Boolean) {
        viewModel.setFragmentState(isVisible)
    }
}