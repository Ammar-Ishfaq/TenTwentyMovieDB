package com.muhammad_ammar.tentwenty.view.activities.base

import androidx.lifecycle.ViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.ParametersDefinition
import kotlin.reflect.KClass

abstract class MainMVVMBaseActivity<out VM : ViewModel>(
    viewModelClass: KClass<VM>,
    viewmodelParameters: ParametersDefinition? = null
) : BaseActivity() {
    protected open val viewModel: VM by viewModel(
        clazz = viewModelClass,
        parameters = viewmodelParameters
    )
}