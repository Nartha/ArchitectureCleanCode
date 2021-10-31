package com.example.thelynx.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding>: AppCompatActivity() {

    private var viewBinding: VB? = null
    protected val binding get() = viewBinding!!

    protected abstract fun getViewBinding(): VB

    abstract fun setUpView(saveInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = getViewBinding()
        setContentView(binding.root)

        setUpView(savedInstanceState)
    }
}