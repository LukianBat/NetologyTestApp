package ru.lukianbat.feature.courses.feature.list.presentation.view

import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
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
        val spannableString = SpannableString(
            context.getString(R.string.courses_list_header_text)
        )
        spannableString.setSpan(
            ForegroundColorSpan(
                context.getColor(R.color.courses_list_header_color)
            ),
            HEADER_COLOR_START_INDEX,
            spannableString.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return CoursesListItem.HeaderItem(spannableString)
    }

    companion object {
        private const val HEADER_COLOR_START_INDEX = 9
    }
}
