package ru.lukianbat.feature.courses.feature.list.presentation.view

import android.text.SpannableString
import ru.lukianbat.feature.courses.feature.list.domain.model.CoursesTheme

sealed class CoursesListItem {

    data class HeaderItem(val headerString: SpannableString) : CoursesListItem()

    data class CoursesThemeItem(val coursesTheme: CoursesTheme) : CoursesListItem()

}
