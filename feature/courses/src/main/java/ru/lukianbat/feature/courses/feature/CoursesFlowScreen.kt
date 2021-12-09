package ru.lukianbat.feature.courses.feature

import androidx.compose.runtime.Composable
import ru.lukianbat.feature.courses.common.di.CoursesFlowComponent
import ru.lukianbat.feature.courses.common.navigation.CoursesFlowNavigationGraph

@Composable
fun CoursesFlowScreen(
    component: CoursesFlowComponent,
    viewModel: CoursesFlowViewModel
) {
    CoursesFlowNavigationGraph(component)
}
