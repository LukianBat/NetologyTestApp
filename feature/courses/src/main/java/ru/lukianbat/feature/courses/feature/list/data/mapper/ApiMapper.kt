package ru.lukianbat.feature.courses.feature.list.data.mapper

import ru.lukianbat.feature.courses.common.data.remote.model.CoursesResponse
import ru.lukianbat.feature.courses.feature.list.domain.model.CoursesTheme

internal object ApiMapper {
    fun CoursesResponse.toDomain() = themes.map {
        CoursesTheme(
            title = it.themeDirection.title,
            coursesCount = it.courses.size
        )
    }
}
