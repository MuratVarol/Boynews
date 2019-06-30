package com.varol.boynews.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.varol.boynews.BR
import org.koin.androidx.viewmodel.ext.android.viewModelByClass
import kotlin.reflect.KClass

/**
 * BaseActivity of all Activities which uses DataBinding and ViewModel
 * Extended Activities only need to do 3 steps
 *  1- Give Activity's ViewModel and Generated DataBinding class as parameter
 *  2- Override getLayoutId field with Activity's layout
 *  3- Enjoy the miracle
 */
abstract class BaseActivity<out VM : ViewModel, DB : ViewDataBinding>(viewModelClass: KClass<VM>) :
    AppCompatActivity() {

    /**
     * ViewModel injector of Koin.
     * no need for ViewModelProviders
     */
    protected val viewModel: VM by viewModelByClass(viewModelClass)

    abstract val layoutRes: Int

    lateinit var binding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)

    }

    /**
     * Loads given fragment to given container layout
     * @param containerId : Activity's container layout,
     * @param fragment: Fragment to be loaded
     * @param addToBackStack : Boolean variable to set if Fragment will added to back stack or not
     */
    fun loadFragment(containerId: Int, fragment: Fragment, addToBackStack: Boolean) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (addToBackStack) {
            fragmentTransaction.addToBackStack("")
        }
        fragmentTransaction.replace(containerId, fragment).commit()
    }

}