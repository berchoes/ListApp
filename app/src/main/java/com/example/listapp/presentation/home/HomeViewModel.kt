package com.example.listapp.presentation.home

import androidx.lifecycle.viewModelScope
import com.example.listapp.base.BaseViewModel
import com.example.listapp.common.Resource
import com.example.listapp.domain.model.PostModel
import com.example.listapp.domain.usecase.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by Berk Ç. on 8.04.2022.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : BaseViewModel() {

    private val _posts = MutableStateFlow<List<PostModel>>(emptyList())
    val posts: StateFlow<List<PostModel>> = _posts

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        getPosts()
    }

    private fun getPosts() =
        getPostsUseCase().onEach {
            when (it) {
                is Resource.Success -> _posts.value = it.data
                is Resource.Error -> _errorMessage.value =  it.errorMessage
                is Resource.Loading -> _isLoading.value = true
            }
        }.launchIn(viewModelScope)
}