package ru.lukianbat.feature.courses.feature.list.data.gateway

import io.reactivex.Single
import ru.lukianbat.feature.courses.feature.list.domain.model.CoursesTheme

interface CoursesRemoteGateway {
    fun getCoursesThemesList(): Single<List<CoursesTheme>>
}
