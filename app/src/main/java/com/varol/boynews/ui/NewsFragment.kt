package com.varol.boynews.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.varol.boynews.R
import com.varol.boynews.base.BaseFragment
import com.varol.boynews.databinding.FragmentNewsListBinding
import com.varol.boynews.extension.informToast
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
        fun newInstance(id: String): NewsFragment {
            val args = Bundle()
            args.putString(KEY_SOURCE_ID, id)
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
        getSelectedSource()?.let {
            viewModel.getAllNews(it)
        } ?: run {
            informToast("Invalid News Source!")
        }

        return binding.root
    }

    private fun subscribeSelectedNews() {
        viewModel.selectedNews.observe(this) {

        }
    }

    private fun getSelectedSource(): String? =
        arguments?.getString(KEY_SOURCE_ID)
}