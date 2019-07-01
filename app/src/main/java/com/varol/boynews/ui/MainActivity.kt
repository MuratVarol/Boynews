package com.varol.boynews.ui

import android.os.Bundle
import com.varol.boynews.R
import com.varol.boynews.base.BaseActivity
import com.varol.boynews.databinding.ActivityMainBinding
import com.varol.boynews.extension.informToast
import com.varol.boynews.viewmodel.MainVM
import observe

class MainActivity : BaseActivity<MainVM,ActivityMainBinding>(MainVM::class) {

    override val layoutRes: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribeErrorMessage()
        subscribeInformMessage()

        loadFragment(R.id.container_main, SourcesFragment(),false)

    }

    private fun subscribeInformMessage() {
        viewModel.informMessage.observe(this) {
            informToast(it.toString())
        }
    }


    private fun subscribeErrorMessage() {
        viewModel.informMessage.observe(this) {
            informToast(it.toString())
        }
    }
}
