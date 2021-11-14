package ru.lukianbat.feature.courses.feature.list.presentation.view

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.lukianbat.coreui.utils.inflate
import ru.lukianbat.feature.courses.R

internal class CoursesListAdapter :
    ListAdapter<CoursesListItem, RecyclerView.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = parent.inflate(viewType)
        return when (viewType) {
            R.layout.item_courses_theme -> CoursesThemeViewHolder(view)
            R.layout.item_courses_header -> HeaderViewHolder(view)
            else -> throw IllegalStateException("unsupported view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position]) {
            is CoursesListItem.CoursesThemeItem -> R.layout.item_courses_theme
            is CoursesListItem.HeaderItem -> R.layout.item_courses_header
            else -> throw IllegalStateException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CoursesThemeViewHolder -> {
                val item = getItem(position) as CoursesListItem.CoursesThemeItem
                holder.bind(item)
            }
            is HeaderViewHolder -> {
                val item = getItem(position) as CoursesListItem.HeaderItem
                holder.bind(item)
            }
        }
    }

    internal object DiffCallback : DiffUtil.ItemCallback<CoursesListItem>() {
        override fun areItemsTheSame(
            oldState: CoursesListItem,
            newState: CoursesListItem
        ): Boolean {
            return oldState is CoursesListItem.HeaderItem && newState is CoursesListItem.HeaderItem ||
                    oldState is CoursesListItem.CoursesThemeItem && newState is CoursesListItem.CoursesThemeItem
        }

        override fun areContentsTheSame(
            oldState: CoursesListItem,
            newState: CoursesListItem
        ): Boolean {
            return oldState == newState
        }
    }
}
