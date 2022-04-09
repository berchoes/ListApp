package com.example.listapp.util

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by Berk Ç. on 9.04.2022.
 */


fun <T> LifecycleOwner.fetch(flowable: Flow<T>, action:(value: T) -> Unit){
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED){
            flowable.collect {
                action(it)
            }
        }
    }
}