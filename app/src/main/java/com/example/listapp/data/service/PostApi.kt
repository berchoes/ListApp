package com.example.listapp.data.service

import com.example.listapp.data.entity.PostDto
import retrofit2.http.GET

/**
 * Created by Berk Ç. on 8.04.2022.
 */

interface PostApi {

    @GET("posts")
    suspend fun getPosts(): List<PostDto>
}