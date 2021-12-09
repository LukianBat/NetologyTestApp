package ru.lukianbat.feature.courses.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import ru.lukianbat.coreutils.di.daggerViewModel
import ru.lukianbat.feature.courses.common.di.CoursesFlowComponent
import ru.lukianbat.feature.courses.feature.list.presentation.CoursesListScreen
import ru.lukianbat.feature.courses.feature.theme.ThemeScreen

private const val ARG_THEME_NAME = "themeName"

sealed class CoursesFlowDestination(val route: String) {

    object CoursesListScreen : CoursesFlowDestination("COURSES_LIST_SCREEN")

    object CoursesThemeScreen : CoursesFlowDestination("COURSES_THEME_SCREEN/{${ARG_THEME_NAME}}") {
        fun createRoute(themeName: String): String = "COURSES_THEME_SCREEN/$themeName"
    }
}

class CoursesFlowActions(navController: NavController) {
    val navigateToCoursesTheme = { themeName: String ->
        navController.navigate(
            CoursesFlowDestination.CoursesThemeScreen.createRoute(themeName)
        )
    }
}

@Composable
fun CoursesFlowNavigationGraph(flowComponent: CoursesFlowComponent) {
    val navController = rememberNavController()

    val actions = remember(navController) { CoursesFlowActions(navController) }

    NavHost(navController, CoursesFlowDestination.CoursesListScreen.route) {
        composable(CoursesFlowDestination.CoursesListScreen.route) {
            val coursesListComponent = flowComponent.coursesListComponent()
                .create()

            CoursesListScreen(
                viewModel = daggerViewModel { coursesListComponent.getViewModel() },
                goToCoursesTheme = actions.navigateToCoursesTheme
            )
        }

        composable(
            route = CoursesFlowDestination.CoursesThemeScreen.route,
            arguments = listOf(navArgument(ARG_THEME_NAME) { type = NavType.StringType }
            )
        ) {
            ThemeScreen(
                it.arguments?.getString(ARG_THEME_NAME) ?: ""
            )
        }
    }
}
