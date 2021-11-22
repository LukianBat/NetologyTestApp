package ru.lukianbat.feature.courses.feature.list.presentation.view

import android.content.Context
import ru.lukianbat.feature.courses.R
import ru.lukianbat.feature.courses.feature.list.domain.model.CoursesTheme
import javax.inject.Inject

class CoursesListMapper @Inject constructor(private val context: Context) {

    fun map(coursesThemes: List<CoursesTheme>): List<CoursesListItem> {
        return mutableListOf<CoursesListItem>()
            .asSequence()
            .plus(getHeaderString())
            .plus(
                coursesThemes.map { CoursesListItem.CoursesThemeItem(it) }
            )
            .toList()
    }

    private fun getHeaderString(): CoursesListItem {
        return CoursesListItem.HeaderItem(
            context.getString(R.string.courses_list_header_text)
        )
    }
}
