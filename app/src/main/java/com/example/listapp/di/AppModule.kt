package com.example.listapp.di

import com.example.listapp.data.repository.PostRepositoryImpl
import com.example.listapp.data.service.PostApi
import com.example.listapp.domain.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Berk Ç. on 8.04.2022.
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    internal fun providePostApi(retrofit: Retrofit): PostApi = retrofit.create(PostApi::class.java)

    @Provides
    @Singleton
    internal fun providePostRepository(api: PostApi): PostRepository = PostRepositoryImpl(api)
}