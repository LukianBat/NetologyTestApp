package ru.lukianbat.feature.courses.feature.list.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.lukianbat.coreutils.di.ViewModelKey
import ru.lukianbat.feature.courses.feature.list.data.gateway.CoursesRemoteGateway
import ru.lukianbat.feature.courses.feature.list.data.gateway.CoursesRemoteGatewayImpl
import ru.lukianbat.feature.courses.feature.list.domain.usecase.GetCoursesThemesUseCase
import ru.lukianbat.feature.courses.feature.list.domain.usecase.GetCoursesThemesUseCaseImpl
import ru.lukianbat.feature.courses.feature.list.presentation.CoursesListViewModel

@Module
abstract class CoursesListModule {

    @Binds
    abstract fun bindCoursesRemoteGateway(impl: CoursesRemoteGatewayImpl): CoursesRemoteGateway

    @Binds
    abstract fun bindGetCoursesThemesUseCase(impl: GetCoursesThemesUseCaseImpl): GetCoursesThemesUseCase

    @Binds
    @IntoMap
    @ViewModelKey(CoursesListViewModel::class)
    abstract fun bindViewModel(viewModel: CoursesListViewModel): ViewModel
}
