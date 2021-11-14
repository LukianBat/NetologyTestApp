import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Test
import ru.lukianbat.feature.courses.common.data.remote.CoursesApi
import ru.lukianbat.feature.courses.common.data.remote.model.CoursesResponse
import ru.lukianbat.feature.courses.feature.list.data.gateway.CoursesRemoteGatewayImpl
import ru.lukianbat.feature.courses.feature.list.domain.model.CoursesTheme

class CoursesRemoteGatewayImplTest {

    private val coursesApi: CoursesApi = mockk(relaxed = true)
    private val coursesRemoteGatewayImpl = CoursesRemoteGatewayImpl(coursesApi)

    @Test
    fun coursesRemoteGatewayImpl_getCoursesThemesList_returnsCorrectList() {
        val firstThemeName = "Marketing"
        val secondThemeName = "Programming"

        val firstTheme = CoursesResponse.Theme(
            listOf(
                CoursesResponse.Course("1234"),
                CoursesResponse.Course("1234")
            ),
            CoursesResponse.ThemeDirection(firstThemeName)
        )
        val secondTheme = CoursesResponse.Theme(
            listOf(
                CoursesResponse.Course("1234"),
                CoursesResponse.Course("12345"),
                CoursesResponse.Course("123456")
            ),
            CoursesResponse.ThemeDirection(secondThemeName)
        )

        every { coursesApi.getCoursesThemes() } returns Single.just(
            CoursesResponse(
                listOf(
                    firstTheme,
                    secondTheme
                )
            )
        )

        val testObserver = coursesRemoteGatewayImpl.getCoursesThemesList()
            .test()

        testObserver.assertValue(
            listOf(
                CoursesTheme(firstThemeName, 2),
                CoursesTheme(secondThemeName, 3),
            )
        )
    }
}
