package ru.lukianbat.feature.courses.feature.list.presentation.list

import ru.lukianbat.feature.courses.feature.list.domain.model.CoursesTheme

sealed class CoursesListItem {

    data class HeaderItem(val headerString: String) : CoursesListItem()

    data class CoursesThemeItem(val coursesTheme: CoursesTheme) : CoursesListItem()
}