package com.example.rickandmorty.feature.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {
    protected open lateinit var viewModel: VM

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    protected abstract fun injectViewModel()
    protected abstract fun injectDependencies()

    protected inline fun <reified T : ViewModel> getViewModel(): T =
        ViewModelProvider(this, viewModelFactory)[T::class.java]

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        injectViewModel()

        super.onCreate(savedInstanceState)
    }
}