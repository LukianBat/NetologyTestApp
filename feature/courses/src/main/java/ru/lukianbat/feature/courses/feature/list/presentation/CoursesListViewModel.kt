package ru.lukianbat.feature.courses.feature.list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.lukianbat.architecture.mvvm.CoreViewModel
import ru.lukianbat.architecture.mvvm.ErrorAdapter
import ru.lukianbat.coreutils.schedulers.BaseSchedulerProvider
import ru.lukianbat.feature.courses.feature.list.domain.usecase.GetCoursesThemesUseCase
import ru.lukianbat.feature.courses.feature.list.presentation.list.CoursesListMapper
import javax.inject.Inject

class CoursesListViewModel @Inject constructor(
    private val getCoursesThemesUseCase: GetCoursesThemesUseCase,
    private val coursesListMapper: CoursesListMapper,
    private val errorAdapter: ErrorAdapter,
    private val baseSchedulerProvider: BaseSchedulerProvider
) : CoreViewModel() {

    val coursesThemesListStateLiveData
        get(): LiveData<ListState> = _coursesThemesListStateLiveData

    private val _coursesThemesListStateLiveData = MutableLiveData<ListState>()

    init {
        loadList()
    }

    override fun onError(throwable: Throwable) {
        _coursesThemesListStateLiveData.value = ListState.ErrorState(
            errorAdapter.adapt(throwable) ?: return
        )
    }

    fun onRetryButtonClicked() {
        loadList()
    }

    private fun loadList() {
        getCoursesThemesUseCase()
            .subscribeOn(baseSchedulerProvider.io())
            .observeOn(baseSchedulerProvider.ui())
            .doOnSubscribe {
                _coursesThemesListStateLiveData.postValue(ListState.LoadingState)
            }
            .subscribe(
                {
                    _coursesThemesListStateLiveData.value =
                        ListState.ItemsState(
                            coursesListMapper.map(it)
                        )
                },
                {
                    onError(it)
                }
            )
            .addDisposable()
    }
}
