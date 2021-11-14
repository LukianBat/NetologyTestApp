package ru.lukianbat.feature.courses.common.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.lukianbat.coreutils.di.ViewModelKey
import ru.lukianbat.feature.courses.common.di.CoursesFlowScope
import ru.lukianbat.feature.courses.feature.CoursesFlowViewModel

@Module
abstract class CoursesFlowModule {

    @Binds
    @IntoMap
    @ViewModelKey(CoursesFlowViewModel::class)
    @CoursesFlowScope
    abstract fun bindViewModel(viewModel: CoursesFlowViewModel): ViewModel
}
