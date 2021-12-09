package ru.lukianbat.feature.courses.common.di

import dagger.Module
import dagger.Subcomponent
import ru.lukianbat.feature.courses.common.di.modules.CoursesFlowModule
import ru.lukianbat.feature.courses.common.di.modules.NetworkModule
import ru.lukianbat.feature.courses.feature.CoursesFlowViewModel
import ru.lukianbat.feature.courses.feature.list.di.CoursesListComponent

@CoursesFlowScope
@Subcomponent(
    modules = [
        CoursesFlowSubcomponentsModule::class,
        NetworkModule::class,
        CoursesFlowModule::class
    ]
)
interface CoursesFlowComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CoursesFlowComponent
    }

    fun coursesListComponent(): CoursesListComponent.Factory

    fun getViewModel(): CoursesFlowViewModel
}

@Module(subcomponents = [CoursesListComponent::class])
object CoursesFlowSubcomponentsModule
