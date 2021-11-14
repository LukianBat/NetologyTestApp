package ru.lukianbat.feature.courses.feature.list.presentation

import ru.lukianbat.feature.courses.feature.list.presentation.view.CoursesListItem

sealed class ListState {

    data class ItemsState(val coursesThemes: List<CoursesListItem>) : ListState()

    object LoadingState : ListState()

    data class ErrorState(val errorMessage: String) : ListState()
}
