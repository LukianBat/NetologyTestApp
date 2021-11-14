package ru.lukianbat.feature.courses.feature

import ru.lukianbat.architecture.mvvm.CoreViewModel
import ru.lukianbat.feature.courses.common.di.CoursesFlowComponentController
import javax.inject.Inject

class CoursesFlowViewModel @Inject constructor(
    private val componentController: CoursesFlowComponentController
) : CoreViewModel() {

    override fun onCleared() {
        super.onCleared()
        componentController.clearCoursesFlowComponent()
    }
}
