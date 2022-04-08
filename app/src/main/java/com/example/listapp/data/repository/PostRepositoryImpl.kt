package com.example.listapp.data.repository

import com.example.listapp.data.entity.PostDto
import com.example.listapp.data.service.PostApi
import com.example.listapp.domain.repository.PostRepository
import javax.inject.Inject

/**
 * Created by Berk Ç. on 8.04.2022.
 */

class PostRepositoryImpl @Inject constructor(private val api: PostApi): PostRepository {

    override suspend fun getPosts(): List<PostDto> = api.getPosts()
}