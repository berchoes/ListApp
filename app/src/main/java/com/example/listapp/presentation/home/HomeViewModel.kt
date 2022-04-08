package com.example.listapp.presentation.home

import com.example.listapp.base.BaseViewModel
import com.example.listapp.domain.usecase.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Berk Ç. on 8.04.2022.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : BaseViewModel(){

}