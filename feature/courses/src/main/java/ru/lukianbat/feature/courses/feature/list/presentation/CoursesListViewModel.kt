package ru.lukianbat.feature.courses.feature.list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.lukianbat.architecture.mvvm.CoreViewModel
import ru.lukianbat.architecture.mvvm.ErrorAdapter
import ru.lukianbat.feature.courses.feature.list.domain.usecase.GetCoursesThemesUseCase
import ru.lukianbat.feature.courses.feature.list.presentation.view.CoursesListMapper
import javax.inject.Inject

class CoursesListViewModel @Inject constructor(
    private val getCoursesThemesUseCase: GetCoursesThemesUseCase,
    private val coursesListMapper: CoursesListMapper,
    private val errorAdapter: ErrorAdapter
) : CoreViewModel() {

    val coursesThemesListStateLiveData
        get(): LiveData<ListState> = _coursesThemesListStateLiveData

    private val _coursesThemesListStateLiveData = MediatorLiveData<ListState>()

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
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
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
