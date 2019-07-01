package com.varol.boynews.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.widget.RxTextView
import com.varol.boynews.R
import com.varol.boynews.base.BaseFragment
import com.varol.boynews.data.models.SourceModel
import com.varol.boynews.databinding.FragmentNewsListBinding
import com.varol.boynews.extension.informToast
import com.varol.boynews.viewmodel.NewsVM
import observe
import java.util.concurrent.TimeUnit


class NewsFragment : BaseFragment<NewsVM, FragmentNewsListBinding>(NewsVM::class) {
    override val getLayoutId: Int = R.layout.fragment_news_list


    companion object {

        const val KEY_SOURCE_ID = "key.source.id"

        /**
         * Creates instance of NewsFragment with added source id bundle
         * @param sourceModel : Source Model
         * @return NewFragment
         */
        fun newInstance(sourceModel: SourceModel): NewsFragment {
            val args = Bundle()
            args.putParcelable(KEY_SOURCE_ID, sourceModel)
            val fragment = NewsFragment()
            fragment.arguments = args
            return fragment
        }

        /**
         * Creates instance of NewsFragment with search ability
         * @return NewFragment
         */
        fun newInstance(): NewsFragment {
            return NewsFragment()
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
            binding.edtSearch.visibility = View.VISIBLE
        }

        subscribeSelectedNews()

        setViewListeners()




        return binding.root
    }

    private fun setViewListeners() {
        binding.ivBack?.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.tvBack?.setOnClickListener {
            activity?.onBackPressed()
        }

        val observableSearch = RxTextView.textChanges(binding.edtSearch)
            .filter { chars -> chars.length >= 3 }
            .debounce(500, TimeUnit.MILLISECONDS)
            .map { chars -> chars.toString() }
            .subscribe {
                viewModel.getSearchedNews(it.toString())
            }


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