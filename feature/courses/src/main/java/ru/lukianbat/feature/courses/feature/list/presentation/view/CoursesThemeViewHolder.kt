package ru.lukianbat.feature.courses.feature.list.presentation.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.lukianbat.coreui.utils.bindView
import ru.lukianbat.feature.courses.R

internal class CoursesThemeViewHolder(
    private val containerView: View
) : RecyclerView.ViewHolder(containerView) {

    private val themeTextView: TextView by bindView(R.id.themeTextView)
    private val coursesCountTextView: TextView by bindView(R.id.coursesCountTextView)

    fun bind(
        item: CoursesListItem.CoursesThemeItem,
    ) {
        themeTextView.text = item.coursesTheme.title
        coursesCountTextView.text = containerView.resources.getString(
            R.string.courses_list_courses_count_text,
            item.coursesTheme.coursesCount
        )
    }
}

