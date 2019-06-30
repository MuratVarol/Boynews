package com.varol.boynews.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.varol.boynews.R
import com.varol.boynews.base.BaseActivity
import com.varol.boynews.databinding.ActivityMainBinding
import com.varol.boynews.viewmodel.MainVM

class MainActivity : BaseActivity<MainVM,ActivityMainBinding>(MainVM::class) {

    override val layoutRes: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadFragment(R.id.container_main, SourcesFragment(),false)

    }
}
