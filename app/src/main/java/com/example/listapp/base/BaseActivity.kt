package com.example.listapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * Created by Berk Ç. on 8.04.2022.
 */

abstract class BaseActivity<VB: ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        onCreateFinished(savedInstanceState)
    }

    protected abstract fun getViewBinding(): VB
    protected abstract fun onCreateFinished(savedInstanceState: Bundle?)

}