package ru.lukianbat.feature.courses.common.data.remote.model

import com.google.gson.annotations.SerializedName

data class CoursesResponse(
    @SerializedName("data") val themes: List<Theme>
) {

    data class Theme(
        @SerializedName("groups") val courses: List<Course>,
        @SerializedName("direction") val themeDirection: ThemeDirection
    )

    data class Course(
        @SerializedName("id") val id: String
    )

    data class ThemeDirection(
        @SerializedName("title") val title: String
    )
}
