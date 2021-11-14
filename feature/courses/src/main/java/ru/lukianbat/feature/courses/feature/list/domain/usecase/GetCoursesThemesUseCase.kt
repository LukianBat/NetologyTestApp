package ru.lukianbat.feature.courses.feature.list.domain.usecase

import io.reactivex.Single
import ru.lukianbat.feature.courses.feature.list.domain.model.CoursesTheme

interface GetCoursesThemesUseCase {
    operator fun invoke(): Single<List<CoursesTheme>>
}
