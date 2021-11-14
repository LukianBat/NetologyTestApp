package ru.lukianbat.netologytest.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.lukianbat.feature.courses.common.di.CoursesFlowComponentController
import javax.inject.Singleton

@Module
class ComponentsControllerModule {

    @Singleton
    @Provides
    fun provideCoursesFlowComponentController(context: Context): CoursesFlowComponentController {
        return (context as CoursesFlowComponentController)
    }
}
