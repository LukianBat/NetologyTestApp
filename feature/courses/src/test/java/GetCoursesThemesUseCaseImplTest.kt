import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Test
import ru.lukianbat.feature.courses.feature.list.data.gateway.CoursesRemoteGateway
import ru.lukianbat.feature.courses.feature.list.domain.EmptyListException
import ru.lukianbat.feature.courses.feature.list.domain.model.CoursesTheme
import ru.lukianbat.feature.courses.feature.list.domain.usecase.GetCoursesThemesUseCaseImpl

class GetCoursesThemesUseCaseImplTest {

    private val coursesRemoteGateway: CoursesRemoteGateway = mockk(relaxed = true)
    private val getCoursesThemesUseCaseImpl: GetCoursesThemesUseCaseImpl =
        GetCoursesThemesUseCaseImpl(coursesRemoteGateway)

    @Test
    fun getCoursesThemesUseCaseImpl_invoke_returns_correctList() {
        val testList = listOf(
            CoursesTheme("marketing", 10)
        )

        every { coursesRemoteGateway.getCoursesThemesList() } returns Single.just(testList)
        val testObserver = getCoursesThemesUseCaseImpl()
            .test()

        testObserver.assertValue(testList)
    }

    @Test
    fun getCoursesThemesUseCaseImpl_invoke_returns_emptyList() {
        val testList = emptyList<CoursesTheme>()

        every { coursesRemoteGateway.getCoursesThemesList() } returns Single.just(testList)

        val testObserver = getCoursesThemesUseCaseImpl()
            .test()

        testObserver.assertError { it is EmptyListException }
    }
}
