package com.varol.boynews.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.varol.boynews.R
import com.varol.boynews.base.BaseFragment
import com.varol.boynews.databinding.FragmentSourcesListBinding
import com.varol.boynews.viewmodel.NewsVM

class SourcesFragment : BaseFragment<NewsVM, FragmentSourcesListBinding>(NewsVM::class) {
    override val getLayoutId: Int = R.layout.fragment_sources_list


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        viewModel.getAllSources()

        return binding.root
    }
}