package ru.lukianbat.netologytest.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import ru.lukianbat.coreutils.di.ViewModelBuilderModule
import ru.lukianbat.feature.courses.common.di.CoursesFlowComponent
import ru.lukianbat.netologytest.di.module.ApplicationModule
import ru.lukianbat.netologytest.di.module.ComponentsControllerModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ComponentsControllerModule::class,
        ViewModelBuilderModule::class,
        SubcomponentsModule::class
    ]
)
interface ApplicationComponent {

    fun coursesFlowComponent(): CoursesFlowComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}

@Module(subcomponents = [CoursesFlowComponent::class])
object SubcomponentsModule
