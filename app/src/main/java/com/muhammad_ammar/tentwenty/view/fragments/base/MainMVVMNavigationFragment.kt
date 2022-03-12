package com.muhammad_ammar.tentwenty.view.fragments.base

import androidx.lifecycle.ViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.ParametersDefinition
import kotlin.reflect.KClass

interface NavigationDestination {

    /** Called by the host when the user interacts with it. */
    fun onUserInteraction() {}
}

abstract class MainNavigationFragment : NavigationDestination, BaseFragment()

abstract class MainMVVMNavigationFragment<out VM : ViewModel>(
    viewModelClass: KClass<VM>,
    viewmodelParameters: ParametersDefinition? = null
) : BaseFragment() {
    protected open val viewModel: VM by viewModel(
        clazz = viewModelClass,
        parameters = viewmodelParameters
    )
}