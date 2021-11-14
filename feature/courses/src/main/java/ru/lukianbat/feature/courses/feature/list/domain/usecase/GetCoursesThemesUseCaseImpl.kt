package ru.lukianbat.feature.courses.feature.list.domain.usecase

import io.reactivex.Single
import ru.lukianbat.feature.courses.feature.list.data.gateway.CoursesRemoteGateway
import ru.lukianbat.feature.courses.feature.list.domain.EmptyListException
import ru.lukianbat.feature.courses.feature.list.domain.model.CoursesTheme
import javax.inject.Inject

class GetCoursesThemesUseCaseImpl @Inject constructor(
    private val coursesRemoteGateway: CoursesRemoteGateway
) : GetCoursesThemesUseCase {
    override fun invoke(): Single<List<CoursesTheme>> {
        return coursesRemoteGateway.getCoursesThemesList()
            .flatMap {
                if (it.isEmpty()) {
                    Single.error(EmptyListException())
                } else {
                    Single.just(it)
                }
            }
    }
}
