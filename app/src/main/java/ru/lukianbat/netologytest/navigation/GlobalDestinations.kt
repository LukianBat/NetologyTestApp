package ru.lukianbat.netologytest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.lukianbat.coreutils.di.daggerViewModel
import ru.lukianbat.feature.courses.feature.CoursesFlowScreen
import ru.lukianbat.netologytest.di.ApplicationComponent

sealed class GlobalDestination(val route: String) {

    object CoursesFlowScreenDestination : GlobalDestination("COURSES_FLOW_SCREEN")
}

class GlobalActions(navController: NavController)

@Composable
fun GlobalNavigationGraph(appComponent: ApplicationComponent) {
    val navController = rememberNavController()

    val actions = remember(navController) { GlobalActions(navController) }

    NavHost(navController, GlobalDestination.CoursesFlowScreenDestination.route) {
        composable(GlobalDestination.CoursesFlowScreenDestination.route) {
            val coursesFlowComponent = appComponent.coursesFlowComponent()
                .create()

            CoursesFlowScreen(
                component = coursesFlowComponent,
                viewModel = daggerViewModel { coursesFlowComponent.getViewModel() },
            )
        }
    }
}
