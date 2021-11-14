package ru.lukianbat.feature.courses.feature.list.presentation.view

import ru.lukianbat.feature.courses.feature.list.domain.model.CoursesTheme

sealed class CoursesListItem {

    data class HeaderItem(val headerString: CharSequence) : CoursesListItem()

    data class CoursesThemeItem(val coursesTheme: CoursesTheme) : CoursesListItem()
}
