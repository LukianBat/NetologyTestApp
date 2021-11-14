import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import ru.lukianbat.architecture.mvvm.ErrorAdapter
import ru.lukianbat.coreutils.schedulers.TestSchedulerProvider
import ru.lukianbat.feature.courses.feature.list.domain.model.CoursesTheme
import ru.lukianbat.feature.courses.feature.list.domain.usecase.GetCoursesThemesUseCase
import ru.lukianbat.feature.courses.feature.list.presentation.CoursesListViewModel
import ru.lukianbat.feature.courses.feature.list.presentation.view.CoursesListMapper

class CoursesListViewModel {

    private val getCoursesThemesUseCase: GetCoursesThemesUseCase = mockk(relaxed = true)
    private val coursesListMapper: CoursesListMapper = mockk(relaxed = true)
    private val errorAdapter: ErrorAdapter = mockk(relaxed = true)

    private lateinit var viewModel: CoursesListViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private fun initViewModel() {
        viewModel = CoursesListViewModel(
            getCoursesThemesUseCase,
            coursesListMapper,
            errorAdapter,
            TestSchedulerProvider()
        )
    }

    @Test
    fun retry_button_clicked() {
        initViewModel()
        viewModel.onRetryButtonClicked()
        verify { getCoursesThemesUseCase() }
    }

    @Test
    fun check_loading_list() {
        val theme = CoursesTheme("marketing", 5)
        val coursesThemes = listOf(theme)
        every { getCoursesThemesUseCase() } returns Single.just(coursesThemes)
        initViewModel()

        verify { coursesListMapper.map(coursesThemes) }
    }

    @Test
    fun check_error_list() {
        val testError = Throwable()
        every { getCoursesThemesUseCase() } returns Single.error(testError)
        initViewModel()

        verify { viewModel.onError(testError) }
    }
}
