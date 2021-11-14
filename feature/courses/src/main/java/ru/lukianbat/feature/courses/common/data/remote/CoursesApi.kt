package ru.lukianbat.feature.courses.common.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import ru.lukianbat.feature.courses.common.data.remote.model.CoursesResponse

interface CoursesApi {
    @GET("master/netology.json")
    fun getCoursesThemes(): Single<CoursesResponse>
}
