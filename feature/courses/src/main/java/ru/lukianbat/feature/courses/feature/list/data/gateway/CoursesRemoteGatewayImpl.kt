package ru.lukianbat.feature.courses.feature.list.data.gateway

import io.reactivex.Single
import ru.lukianbat.feature.courses.common.data.remote.CoursesApi
import ru.lukianbat.feature.courses.common.di.CoursesFlowScope
import ru.lukianbat.feature.courses.feature.list.data.mapper.ApiMapper.toDomain
import ru.lukianbat.feature.courses.feature.list.domain.model.CoursesTheme
import javax.inject.Inject

@CoursesFlowScope
class CoursesRemoteGatewayImpl @Inject constructor(
    private val coursesApi: CoursesApi
) : CoursesRemoteGateway {
    override fun getCoursesThemesList(): Single<List<CoursesTheme>> {
        return coursesApi.getCoursesThemes()
            .map { it.toDomain() }
    }
}
