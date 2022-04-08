package com.example.listapp.domain.repository

import com.example.listapp.data.entity.PostDto

/**
 * Created by Berk Ç. on 8.04.2022.
 */
interface PostRepository {

    suspend fun getPosts(): List<PostDto>
}