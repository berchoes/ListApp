package com.example.listapp.domain.usecase

import com.example.listapp.common.Resource
import com.example.listapp.data.entity.toPostModel
import com.example.listapp.domain.model.PostModel
import com.example.listapp.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Berk Ç. on 8.04.2022.
 */

class GetPostsUseCase @Inject constructor(private val repository: PostRepository) {

    operator fun invoke(): Flow<Resource<List<PostModel>>> = flow {
        try {
            val posts = repository.getPosts().map { it.toPostModel() }
            emit(Resource.Success<List<PostModel>>(posts))
        }catch (e: Exception){
            emit(Resource.Error(e.localizedMessage ?: "Something went wrong"))
        }
    }
}