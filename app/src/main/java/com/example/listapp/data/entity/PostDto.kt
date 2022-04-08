package com.example.listapp.data.entity

import com.example.listapp.domain.model.PostModel


data class PostDto(
    val body: String?,
    val id: Int,
    val title: String?,
    val userId: Int
)


fun PostDto.toPostModel() = PostModel(
    body = this.body ?: "Empty Body",
    title = this.title ?: "No Title"
)