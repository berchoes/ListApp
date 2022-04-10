package com.example.listapp.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.listapp.common.SEND_POST_TO_DETAIL_PAGE
import com.example.listapp.domain.model.PostModel
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Berk Ç. on 9.04.2022.
 */

@HiltViewModel
class PostDetailsViewModel @Inject constructor(
   private val savedStateHandle: SavedStateHandle
) : ViewModel() {

     var currentPost: PostModel? = null
        get() {
            val postJson = savedStateHandle.get<String>(SEND_POST_TO_DETAIL_PAGE)
            val post: PostModel = Gson().fromJson(postJson,PostModel::class.java)
            field = post
            return field
        }
}