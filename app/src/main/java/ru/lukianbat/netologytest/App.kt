package ru.lukianbat.netologytest

import android.app.Application
import ru.lukianbat.coreutils.utils.MutableLazy
import ru.lukianbat.feature.courses.common.di.CoursesFlowComponent
import ru.lukianbat.feature.courses.common.di.CoursesFlowComponentController
import ru.lukianbat.netologytest.di.ApplicationComponent
import ru.lukianbat.netologytest.di.DaggerApplicationComponent

class App : Application(), CoursesFlowComponentController {

    private lateinit var appComponent: ApplicationComponent

    private val citiesFlowComponent = MutableLazy.resettableLazy {
        appComponent.coursesFlowComponent().create()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.factory().create(applicationContext)
    }

    override fun provideCoursesFlowComponent(): CoursesFlowComponent {
        return requireNotNull(citiesFlowComponent.value)
    }

    override fun clearCoursesFlowComponent() {
        citiesFlowComponent.reset()
    }
}
