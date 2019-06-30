package com.varol.boynews.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.varol.boynews.R
import com.varol.boynews.base.BaseFragment
import com.varol.boynews.databinding.FragmentSourcesListBinding
import com.varol.boynews.extension.informToast
import com.varol.boynews.viewmodel.NewsVM
import observe

class SourcesFragment : BaseFragment<NewsVM, FragmentSourcesListBinding>(NewsVM::class) {
    override val getLayoutId: Int = R.layout.fragment_sources_list


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        subscribeSelectedSource()

        viewModel.getAllSources()

        return binding.root
    }

    /**
     * Observing selectedSource LiveData
     * If selected source is not null; call NewsFragment with source argument else inform with toast
     */
    private fun subscribeSelectedSource() {
        viewModel.selectedSource.observe(this) { sourceModel ->
            sourceModel?.let { sourceModel ->
                loadFragment(
                    R.id.container_main,
                    NewsFragment.newInstance(sourceModel),
                    fragmentManager,
                    true
                )
            } ?: run {
                informToast("Invalid News Source!")
            }
        }
    }
}