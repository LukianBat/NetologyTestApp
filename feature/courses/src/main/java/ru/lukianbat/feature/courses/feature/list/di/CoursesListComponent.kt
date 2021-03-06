package ru.lukianbat.feature.courses.feature.list.di

import dagger.Subcomponent
import ru.lukianbat.feature.courses.feature.list.presentation.CoursesListFragment

@Subcomponent(modules = [CoursesListModule::class])
interface CoursesListComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CoursesListComponent
    }

    fun inject(fragment: CoursesListFragment)
}
