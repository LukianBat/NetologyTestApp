package ru.lukianbat.feature.courses.feature.list.presentation.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.lukianbat.coreui.utils.bindView
import ru.lukianbat.feature.courses.R

internal class HeaderViewHolder(
    containerView: View
) : RecyclerView.ViewHolder(containerView) {

    private val headerTextView: TextView by bindView(R.id.headerTextView)

    fun bind(
        item: CoursesListItem.HeaderItem,
    ) {
        headerTextView.text = item.headerString
    }
}

