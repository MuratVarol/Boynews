package com.varol.boynews.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.varol.boynews.BR
import org.koin.androidx.viewmodel.ext.android.viewModelByClass
import kotlin.reflect.KClass


/**
 * BaseFragment of all Fragments which uses DataBinding and ViewModel
 * Extended fragments only need to do 4 steps
 *  1- Give Fragment's ViewModel and Generated DataBinding class as parameter
 *  2- Override getLayoutId field with Fragment's layout
 *  3- Return dataBinding.root instead of super.onCreateView()
 *  4- Enjoy the miracle
 */
abstract class BaseFragment<out VM : ViewModel, DB : ViewDataBinding>(viewModelClass: KClass<VM>) : Fragment() {

    /**
     * ViewModel injector
     * no need for ViewModelProviders
     */
    val viewModel: VM by viewModelByClass(viewModelClass)

    protected lateinit var binding: DB

    abstract val getLayoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId, container, false)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
        return binding.root
    }


    /**
     * Loads given fragment to given container layout
     * @param containerId : Activity's container layout,
     * @param fragment: Fragment to be loaded
     * @param fm: Fragment Manager
     * @param addToBackStack : Boolean variable to set if Fragment will added to back stack or not
     */
    fun loadFragment(
        containerId: Int,
        fragment: Fragment,
        fm: FragmentManager?,
        addToBackStack: Boolean
    ) {
        val ft = fm?.beginTransaction()
        if (addToBackStack) {
            ft?.addToBackStack("")
        }
        ft?.add(containerId, fragment)?.commit()
    }

    /**
     * Clears all stack from FragmentManager
     * @param manager : FragmentManager
     */
    fun clearBackStack(manager: FragmentManager?) {
        manager?.apply {
            if (backStackEntryCount > 1) {
                val first = getBackStackEntryAt(1)
                popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
        }

    }


}