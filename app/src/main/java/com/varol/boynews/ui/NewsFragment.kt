package com.varol.boynews.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.varol.boynews.R
import com.varol.boynews.base.BaseFragment
import com.varol.boynews.databinding.FragmentNewsListBinding
import com.varol.boynews.extension.informToast
import com.varol.boynews.models.SourceModel
import com.varol.boynews.viewmodel.NewsVM
import observe


class NewsFragment : BaseFragment<NewsVM, FragmentNewsListBinding>(NewsVM::class) {
    override val getLayoutId: Int = R.layout.fragment_news_list


    companion object {

        const val KEY_SOURCE_ID = "key.source.id"

        /**
         * Creates instance of NewsFragment with added source id bundle
         * @param id : Source id
         * @return NewFragment
         */
        fun newInstance(sourceModel: SourceModel): NewsFragment {
            val args = Bundle()
            args.putParcelable(KEY_SOURCE_ID, sourceModel)
            val fragment = NewsFragment()
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        //Call getAllNews method if source is not null
        getSelectedSource()?.let { sourceModel ->

            viewModel.selectedSource.postValue(sourceModel)

            sourceModel.id?.let { sourceId ->
                viewModel.getNewsPeriodically(sourceId)
            }

        } ?: run {
            informToast("Invalid News Source!")
        }

        subscribeSelectedNews()

        binding.ivBack?.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.tvBack?.setOnClickListener {
            activity?.onBackPressed()
        }

        return binding.root
    }

    private fun subscribeSelectedNews() {
        viewModel.selectedNews.observe(this) {
            it?.urlAsId?.let { url ->
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(browserIntent)
            } ?: run {
                informToast("Invalid URL!")
            }

        }
    }

    private fun getSelectedSource(): SourceModel? =
        arguments?.getParcelable(KEY_SOURCE_ID)
}